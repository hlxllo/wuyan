package vip.xiaozhao.intern.baseUtil.intf.service;

import vip.xiaozhao.intern.baseUtil.intf.entity.Question;
import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;
import vip.xiaozhao.intern.baseUtil.intf.enums.QuestionTypeEnum;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    // 查询问题类别
    Map<Integer, String> getAllQuestionTypes();

    // 根据名称查询话题
    List<Topic> getTopicsByName(String name);

    // 添加问题
    Integer insertQuestion(Question question);
}
