package vip.xiaozhao.intern.baseUtil.controller.detail;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaozhao.intern.baseUtil.controller.BaseController;
import vip.xiaozhao.intern.baseUtil.intf.dto.ResponseDO;
import vip.xiaozhao.intern.baseUtil.intf.service.DetailService;
import vip.xiaozhao.intern.baseUtil.intf.vo.QuestionDetailVo;

@RestController
@RequestMapping("/wuyan/detail")
@Validated
public class DetailController extends BaseController {
    @Override
    protected int getCurrentUserId(HttpServletRequest request) {
        return super.getCurrentUserId(request);
    }

    @Resource
    private DetailService detailService;

    // 查询问题详情
    @GetMapping("/{id}")
    public ResponseDO getQuestionDetail(@PathVariable int id) {
        QuestionDetailVo vo = detailService.getQuestionDetail(id);
        return ResponseDO.success(vo);
    }
}
