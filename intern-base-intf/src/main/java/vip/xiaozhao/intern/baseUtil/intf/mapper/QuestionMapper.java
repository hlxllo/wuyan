package vip.xiaozhao.intern.baseUtil.intf.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vip.xiaozhao.intern.baseUtil.intf.entity.Question;
import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;

import java.util.List;

@Mapper
public interface QuestionMapper {

    List<Topic> getTopicsByName(String name);

    Topic getTopicByIdAndName(@Param("id") Integer id, @Param("name") String name);

    void insertTopic(Topic topic);

    void insertQuestion(Question question);

    void insertQuestionTopicRelation(@Param("questionId") Integer questionId, @Param("topicId") Integer topicId);
}
