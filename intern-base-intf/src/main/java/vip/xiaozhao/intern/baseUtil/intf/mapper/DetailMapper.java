package vip.xiaozhao.intern.baseUtil.intf.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.xiaozhao.intern.baseUtil.intf.entity.Answer;
import vip.xiaozhao.intern.baseUtil.intf.vo.QuestionDetailVo;

/**
 * @author dogofayaka
 */
@Mapper
public interface DetailMapper {

    QuestionDetailVo getQuestionDetailById(int id);

    void addAnswer(Answer answer);
}
