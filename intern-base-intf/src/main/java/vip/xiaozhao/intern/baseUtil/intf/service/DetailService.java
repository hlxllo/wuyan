package vip.xiaozhao.intern.baseUtil.intf.service;

import vip.xiaozhao.intern.baseUtil.intf.entity.Answer;
import vip.xiaozhao.intern.baseUtil.intf.vo.AnswerDetailVo;
import vip.xiaozhao.intern.baseUtil.intf.vo.QuestionDetailVo;

import java.util.List;

/**
 * @author dogofayaka
 */
public interface DetailService {
    // 获取问题详情
    QuestionDetailVo getQuestionDetail(int id);
    // 查询回答列表
    List<AnswerDetailVo> listAnswers(int id, int rule, int page);
    // 提交回答
    int addAnswer(Answer answer);
}
