package vip.xiaozhao.intern.baseUtil.intf.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.xiaozhao.intern.baseUtil.intf.entity.Answer;
import vip.xiaozhao.intern.baseUtil.intf.vo.AnswerDetailVo;
import vip.xiaozhao.intern.baseUtil.intf.vo.QuestionDetailVo;

import java.util.List;

/**
 * @author dogofayaka
 */
@Mapper
public interface DetailMapper {

    QuestionDetailVo getQuestionDetailById(int id);

    void addAnswer(Answer answer);

    List<AnswerDetailVo> listAnswersByIdAndHeat(int id, int limit, int offset);

    List<AnswerDetailVo> listAnswersByIdAndAddTime(int id, int limit, int offset);
}
