package vip.xiaozhao.intern.baseUtil.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;
import vip.xiaozhao.intern.baseUtil.intf.mapper.TopicMapper;
import vip.xiaozhao.intern.baseUtil.intf.service.TopicService;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    private TopicMapper topicMapper;

    @Override
    public List<Topic> getTopicsByName(String name) {
        List<Topic> topics = topicMapper.getTopicsByName(name);
        return topics;
    }
}
