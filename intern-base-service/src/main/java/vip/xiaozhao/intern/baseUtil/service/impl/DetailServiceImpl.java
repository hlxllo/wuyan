package vip.xiaozhao.intern.baseUtil.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaozhao.intern.baseUtil.intf.entity.Answer;
import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;
import vip.xiaozhao.intern.baseUtil.intf.mapper.DetailMapper;
import vip.xiaozhao.intern.baseUtil.intf.mapper.QuestionMapper;
import vip.xiaozhao.intern.baseUtil.intf.mapper.TopicMapper;
import vip.xiaozhao.intern.baseUtil.intf.service.DetailService;
import vip.xiaozhao.intern.baseUtil.intf.service.UserService;
import vip.xiaozhao.intern.baseUtil.intf.vo.AnswerBasicVo;
import vip.xiaozhao.intern.baseUtil.intf.vo.QuestionDetailVo;
import vip.xiaozhao.intern.baseUtil.intf.vo.UserBasicVo;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {

    @Resource
    private DetailMapper detailMapper;

    @Resource
    private UserService userService;

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public QuestionDetailVo getQuestionDetail(int id) {
        if (id <= 0) {
            throw new RuntimeException("id 不存在");
        }
        // 根据 id 查询问题
        QuestionDetailVo vo = detailMapper.getQuestionDetailById(id);
        if (vo == null) {
            throw new RuntimeException("问题不存在");
        }
        // 根据 id 查询话题
        List<Integer> topicIds = questionMapper.getTopicIds(id);
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
    public List<AnswerBasicVo> listAnswers(int rule) {
        return List.of();
    }

    @Transactional
    @Override
    public int addAnswer(Answer answer) {
        int userId = answer.getUserId();
        int questionId = answer.getQuestionId();
        UserBasicVo userBasic = userService.getUserBasic(userId);
        // TODO 暂时先用详细的顶着
        QuestionDetailVo questionDetail = detailMapper.getQuestionDetailById(questionId);
        if (ObjUtil.isEmpty(userBasic) || ObjUtil.isEmpty(questionDetail)) {
            throw new RuntimeException("用户或问题不存在");
        }
        detailMapper.addAnswer(answer);
        int id = answer.getId();
        if (id <= 0) {
            throw new RuntimeException("发布问题失败");
        }
        return id;
    }
}
