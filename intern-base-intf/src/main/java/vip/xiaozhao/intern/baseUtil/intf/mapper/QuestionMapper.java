package vip.xiaozhao.intern.baseUtil.intf.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;

import java.util.List;

@Mapper
public interface QuestionMapper {

    List<Topic> getTopicsByName(String name);
}
