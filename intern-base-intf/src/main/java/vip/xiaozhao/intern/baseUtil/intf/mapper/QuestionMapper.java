package vip.xiaozhao.intern.baseUtil.intf.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vip.xiaozhao.intern.baseUtil.intf.entity.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {

    void insertQuestion(Question question);

    void insertQuestionTopicRelation(@Param("questionId") Integer questionId, @Param("topicId") Integer topicId);

    List<Integer> getTopicIds(int id);
}
