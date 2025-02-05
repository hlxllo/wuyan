package vip.xiaozhao.intern.baseUtil.intf.service;

import vip.xiaozhao.intern.baseUtil.intf.entity.Question;
import vip.xiaozhao.intern.baseUtil.intf.vo.HotQuestionVo;
import vip.xiaozhao.intern.baseUtil.intf.vo.QuestionDetailVo;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    // 查询问题类别
    Map<Integer, String> getAllQuestionTypes();

    // 添加问题
    int insertQuestion(Question question);

    // 获取问题详情
    QuestionDetailVo getQuestionDetail(int id);

    List<HotQuestionVo> listHotQuestions();
}
