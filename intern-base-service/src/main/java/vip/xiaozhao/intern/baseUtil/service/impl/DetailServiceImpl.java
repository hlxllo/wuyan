package vip.xiaozhao.intern.baseUtil.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;
import vip.xiaozhao.intern.baseUtil.intf.mapper.DetailMapper;
import vip.xiaozhao.intern.baseUtil.intf.mapper.QuestionMapper;
import vip.xiaozhao.intern.baseUtil.intf.mapper.TopicMapper;
import vip.xiaozhao.intern.baseUtil.intf.mapper.UserMapper;
import vip.xiaozhao.intern.baseUtil.intf.service.DetailService;
import vip.xiaozhao.intern.baseUtil.intf.utils.ConvertUtils;
import vip.xiaozhao.intern.baseUtil.intf.vo.QuestionDetailVo;
import vip.xiaozhao.intern.baseUtil.intf.vo.UserBasicVo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DetailServiceImpl implements DetailService {

    @Resource
    private DetailMapper detailMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public QuestionDetailVo getQuestionDetail(int id) {
        if (id == 0) {
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
        Object o = redisTemplate.opsForValue().get(userId);
        UserBasicVo userVo;
        if (o == null) {
            // 如果没查到，去数据库查，并放入缓存
            userVo = userMapper.getUserBasicById(userId);
            if (ObjUtil.isEmpty(userVo)) {
                throw new RuntimeException("用户不存在");
            }
            redisTemplate.opsForValue().set(userId, userVo, 5, TimeUnit.MINUTES);
        } else {
            userVo = ConvertUtils.convertObject(o, UserBasicVo.class);
        }
        vo.setUserVo(userVo);
        return vo;
    }
}
