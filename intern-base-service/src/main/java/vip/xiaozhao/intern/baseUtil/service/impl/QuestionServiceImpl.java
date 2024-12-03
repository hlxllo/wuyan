package vip.xiaozhao.intern.baseUtil.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import vip.xiaozhao.intern.baseUtil.intf.entity.Question;
import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;
import vip.xiaozhao.intern.baseUtil.intf.enums.QuestionTypeEnum;
import vip.xiaozhao.intern.baseUtil.intf.mapper.QuestionMapper;
import vip.xiaozhao.intern.baseUtil.intf.service.QuestionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public Map<Integer, String> getAllQuestionTypes() {
        HashMap<Integer, String> typeMap = new HashMap<>();
        QuestionTypeEnum[] types = QuestionTypeEnum.values();
        for (QuestionTypeEnum type : types) {
            typeMap.put(type.getNumber(), type.getType());
        }
        return typeMap;
    }

    @Override
    public List<Topic> getTopicsByName(String name) {
        List<Topic> topics = questionMapper.getTopicsByName(name);
        return topics;
    }

    @Override
    public Integer insertQuestion(Question question) {
        // 验证标题
        String title = question.getTitle();
        if (title.length() < 5 || title.length() > 34) {
            throw new RuntimeException("标题长度不符合要求");
        }
        return null;
    }
}
