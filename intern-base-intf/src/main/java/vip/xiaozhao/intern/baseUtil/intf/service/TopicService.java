package vip.xiaozhao.intern.baseUtil.intf.service;

import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;

import java.util.List;

public interface TopicService {
    // 根据名称查询话题
    List<Topic> getTopicsByName(String name);
}
