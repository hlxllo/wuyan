package vip.xiaozhao.intern.baseUtil.controller.question;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.xiaozhao.intern.baseUtil.controller.BaseController;
import vip.xiaozhao.intern.baseUtil.intf.dto.ResponseDO;
import vip.xiaozhao.intern.baseUtil.intf.entity.Question;
import vip.xiaozhao.intern.baseUtil.intf.service.QuestionService;

import java.util.List;
import java.util.Map;

// 提问模块
@RestController
@RequestMapping("/wuyan/question")
@Validated
public class QuestionController extends BaseController {
    @Override
    protected int getCurrentUserId(HttpServletRequest request) {
        return super.getCurrentUserId(request);
    }

    @Resource
    private QuestionService questionService;

    // 获取所有问题类别
    @GetMapping("/type")
    public ResponseDO getAllQuestionTypes() {
        Map<Integer, String> types = questionService.getAllQuestionTypes();
        return ResponseDO.success(types);
    }

    // 添加问题
    @PostMapping("/commit")
    public ResponseDO insertQuestion(@Valid @RequestBody Question question) {
        int id = questionService.insertQuestion(question);
        return ResponseDO.success(id);
    }
}
