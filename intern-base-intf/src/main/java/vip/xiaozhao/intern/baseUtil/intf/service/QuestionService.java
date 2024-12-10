package vip.xiaozhao.intern.baseUtil.intf.service;

import vip.xiaozhao.intern.baseUtil.intf.entity.Question;
import java.util.Map;

public interface QuestionService {
    // 查询问题类别
    Map<Integer, String> getAllQuestionTypes();

    // 添加问题
    int insertQuestion(Question question);
}
