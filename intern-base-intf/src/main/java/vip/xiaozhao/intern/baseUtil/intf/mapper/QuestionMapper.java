package vip.xiaozhao.intern.baseUtil.intf.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vip.xiaozhao.intern.baseUtil.intf.entity.Question;
import vip.xiaozhao.intern.baseUtil.intf.vo.HotQuestionVo;
import vip.xiaozhao.intern.baseUtil.intf.vo.QuestionDetailVo;

import java.util.List;

@Mapper
public interface QuestionMapper {

    void insertQuestion(Question question);

    void insertQuestionTopicRelation(@Param("questionId") Integer questionId, @Param("topicId") Integer topicId);

    QuestionDetailVo getQuestionDetailById(int id);

    List<HotQuestionVo> listQuestionsBefore6();

    List<HotQuestionVo> listQuestionsAfter6();
}
