package vip.xiaozhao.intern.baseUtil.controller.answer;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.xiaozhao.intern.baseUtil.controller.BaseController;
import vip.xiaozhao.intern.baseUtil.intf.dto.ResponseDO;
import vip.xiaozhao.intern.baseUtil.intf.entity.Answer;
import vip.xiaozhao.intern.baseUtil.intf.service.AnswerService;
import vip.xiaozhao.intern.baseUtil.intf.vo.AnswerDetailVo;

import java.util.List;

/**
 * @author dogofayaka
 */
@RestController
@RequestMapping("/wuyan/answers")
@Validated
public class AnswerController extends BaseController {
    @Override
    protected int getCurrentUserId(HttpServletRequest request) {
        return super.getCurrentUserId(request);
    }

    @Resource
    private AnswerService answerService;

    // 查询回答列表
    @GetMapping("/{id}")
    public ResponseDO listAnswers(@PathVariable int id, @RequestParam int rule, @RequestParam int page) {
        if (id <= 0) {
            return ResponseDO.fail("问题不存在");
        }
        if (rule != 1 && rule != 2) {
            return ResponseDO.fail("规则错误");
        }
        if (page <= 0) {
            return ResponseDO.fail("页码不存在");
        }
        List<AnswerDetailVo> vos = answerService.listAnswers(id, rule, page);
        return ResponseDO.success(vos);
    }

    // 发布回答
    @PostMapping
    public ResponseDO commitAnswer(@RequestBody Answer answer) {
        if (ObjUtil.isEmpty(answer)) {
            return ResponseDO.fail("回答不能为空");
        }
        if (answer.getUserId() <= 0 || answer.getQuestionId() <= 0 || StrUtil.isEmpty(answer.getContent())) {
            return ResponseDO.fail("回答内容不完整");
        }
        String urls = answer.getUrls();
        if (!StrUtil.isEmpty(urls) && urls.split(",").length > 9) {
            return ResponseDO.fail("图片数量超过上限");
        }
        int id = answerService.addAnswer(answer);
        return ResponseDO.success(id);
    }
}
