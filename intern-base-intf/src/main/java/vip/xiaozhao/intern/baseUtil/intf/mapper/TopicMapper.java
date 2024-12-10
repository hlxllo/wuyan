package vip.xiaozhao.intern.baseUtil.intf.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;

import java.util.List;

@Mapper
public interface TopicMapper {

    List<Topic> getTopicsByName(String name);

    void insertTopic(Topic topic);

    Topic getTopicById(Integer id);

    Topic getTopicByName(String name);
}
