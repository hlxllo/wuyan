package vip.xiaozhao.intern.baseUtil.service.impl;

import cn.hutool.core.util.ObjUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaozhao.intern.baseUtil.intf.entity.Image;
import vip.xiaozhao.intern.baseUtil.intf.entity.Question;
import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;
import vip.xiaozhao.intern.baseUtil.intf.entity.User;
import vip.xiaozhao.intern.baseUtil.intf.enums.QuestionTypeEnum;
import vip.xiaozhao.intern.baseUtil.intf.mapper.ImageMapper;
import vip.xiaozhao.intern.baseUtil.intf.mapper.QuestionMapper;
import vip.xiaozhao.intern.baseUtil.intf.mapper.TopicMapper;
import vip.xiaozhao.intern.baseUtil.intf.mapper.UserMapper;
import vip.xiaozhao.intern.baseUtil.intf.service.QuestionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
