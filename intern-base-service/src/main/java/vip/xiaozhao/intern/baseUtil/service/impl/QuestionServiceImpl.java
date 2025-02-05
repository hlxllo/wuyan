package vip.xiaozhao.intern.baseUtil.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaozhao.intern.baseUtil.intf.constant.QueryConstant;
import vip.xiaozhao.intern.baseUtil.intf.constant.RedisConstant;
import vip.xiaozhao.intern.baseUtil.intf.entity.Image;
import vip.xiaozhao.intern.baseUtil.intf.entity.Question;
import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;
import vip.xiaozhao.intern.baseUtil.intf.entity.User;
import vip.xiaozhao.intern.baseUtil.intf.enums.QuestionTypeEnum;
import vip.xiaozhao.intern.baseUtil.intf.mapper.*;
import vip.xiaozhao.intern.baseUtil.intf.service.QuestionService;
import vip.xiaozhao.intern.baseUtil.intf.service.UserService;
import vip.xiaozhao.intern.baseUtil.intf.utils.ConvertUtils;
import vip.xiaozhao.intern.baseUtil.intf.vo.HotQuestionVo;
import vip.xiaozhao.intern.baseUtil.intf.vo.QuestionDetailVo;
import vip.xiaozhao.intern.baseUtil.intf.vo.UserBasicVo;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private ImageMapper imageMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Map<Integer, String> getAllQuestionTypes() {
        HashMap<Integer, String> typeMap = new HashMap<>();
        QuestionTypeEnum[] types = QuestionTypeEnum.values();
        for (QuestionTypeEnum type : types) {
            typeMap.put(type.getNumber(), type.getType());
        }
        return typeMap;
    }

    @Override
    @Transactional
    public int insertQuestion(Question question) {
        // 验证用户是否存在
        Integer userId = question.getUserId();
        User user = userMapper.getUserById(userId);
        if (ObjUtil.isEmpty(user)) {
            throw new RuntimeException("用户不存在");
        }
        // 验证标题
        String title = question.getTitle();
        if (title.length() < 5 || title.length() > 34) {
            throw new RuntimeException("标题长度不符合要求");
        }
        // 验证类别
        Integer number = question.getType();
        // 类型不存在会报错
        QuestionTypeEnum.getType(number);
        // 验证话题，如果有 id，根据 id 和名称查询；id 为 -1 则新增
        List<Topic> topics = question.getTopics();
        for (Topic topic : topics) {
            Integer id = topic.getId();
            String name = topic.getName();
            if (id != -1) {
                Topic result = topicMapper.getTopicById(id);
                if (result == null) {
                    throw new RuntimeException("错误的话题");
                }
            } else {
                Topic result = topicMapper.getTopicByName(name);
                if (result != null) {
                    throw new RuntimeException("话题已存在");
                }
                topicMapper.insertTopic(topic);
            }
        }
        // 验证图片
        List<Integer> ids = question.getImageIds();
        // 最多 3 张
        if (ids.size() > 3) {
            throw new RuntimeException("图片数量超过上限");
        }
        String urls = "";
        for (Integer id : ids) {
            // 根据 id 查询图片，没有就报错
            Image image = imageMapper.getImageById(id);
            if (image == null) {
                throw new RuntimeException("图片不存在");
            }
            urls += "," + image.getUrl();
        }
        question.setUrls(urls);
        // 插入话题，拿到 id 后插入关联表
        questionMapper.insertQuestion(question);
        Integer questionId = question.getId();
        // 插入关联表
        for (Topic topic : topics) {
            Integer topicId = topic.getId();
            questionMapper.insertQuestionTopicRelation(questionId, topicId);
        }
        return questionId;
    }

    @Override
    public QuestionDetailVo getQuestionDetail(int id) {
        if (id <= 0) {
            throw new RuntimeException("id 不存在");
        }
        // 根据 id 查询问题
        QuestionDetailVo vo = questionMapper.getQuestionDetailById(id);
        if (vo == null) {
            throw new RuntimeException("问题不存在");
        }
        // 根据 id 查询话题
        List<Integer> topicIds = topicMapper.getTopicIds(id);
        if (CollUtil.isEmpty(topicIds)) {
            throw new RuntimeException("话题不存在");
        }
        List<Topic> topics = new ArrayList<>();
        for (Integer topicId : topicIds) {
            Topic topic = topicMapper.getTopicById(topicId);
            topics.add(topic);
        }
        vo.setTopics(topics);
        // 根据 userId 查询用户基本信息
        int userId = vo.getUserId();
        UserBasicVo userVo = userService.getUserBasic(userId);
        vo.setUserVo(userVo);
        return vo;
    }

    @Override
    public List<HotQuestionVo> listHotQuestions() {
        // 查缓存，不存在则缓存
        String key = RedisConstant.HOT_QUESTION;
        Object o = redisTemplate.opsForValue().get(key);
        List<HotQuestionVo> vos;
        // 如果查到了，直接返回
        if (o != null) {
            vos = ConvertUtils.convert2List(o, HotQuestionVo.class);
        } else {
            // 如果为空就去数据库查
            // 先分时段查询
            LocalDateTime now = LocalDateTime.now();
            int hour = now.getHour();
            // 6 点之前，查询昨天22点之后的数据
            if (hour < 6) {
                vos = questionMapper.listQuestionsBefore6();
            } else {
                // 查询今天0点之后的数据
                vos = questionMapper.listQuestionsAfter6();
            }
            if (vos == null) {
                throw new RuntimeException("问题不存在");
            }
            // 构造过滤规则
            // (heat + 1) / (time + 2) ^ G，且每种前20
            Instant nowInstant = Instant.now();
            Map<Integer, List<HotQuestionVo>> top20ByType = vos.stream()
                    .sorted(Comparator.comparingDouble((HotQuestionVo vo) ->
                            (vo.getHeat() + 1) / Math.pow(
                                    Duration.between(vo.getAddTime().toInstant(), nowInstant).toHours() + 2,
                                    QueryConstant.FACTOR)
                    ).reversed())
                    .collect(Collectors.groupingBy(HotQuestionVo::getType,
                            Collectors.collectingAndThen(Collectors.toList(), (List<HotQuestionVo> list) ->
                                    list.stream().limit(20).toList())));

            vos = top20ByType.values().stream()
                    .flatMap(List::stream)
                    .sorted(Comparator.comparingDouble((HotQuestionVo vo) ->
                            (vo.getHeat() + 1) / Math.pow(
                                    Duration.between(vo.getAddTime().toInstant(), nowInstant).toHours() + 2,
                                    QueryConstant.FACTOR)
                    ).reversed())
                    .toList();
            // 放缓存
            redisTemplate.opsForValue().set(key, vos, 2, TimeUnit.MINUTES);
        }
        return vos;
    }
}
