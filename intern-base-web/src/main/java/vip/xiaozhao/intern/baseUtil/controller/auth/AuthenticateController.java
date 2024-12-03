package vip.xiaozhao.intern.baseUtil.controller.auth;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.xiaozhao.intern.baseUtil.controller.BaseController;
import vip.xiaozhao.intern.baseUtil.intf.dto.ResponseDO;
import vip.xiaozhao.intern.baseUtil.intf.entity.Authenticate;
import vip.xiaozhao.intern.baseUtil.intf.service.AuthenticateService;

//认证模块
@RestController
@RequestMapping("/wuyan/authenticate")
@Validated
public class AuthenticateController extends BaseController {
    @Override
    protected int getCurrentUserId(HttpServletRequest request) {
        return super.getCurrentUserId(request);
    }

    @Resource
    private AuthenticateService authenticateService;

    // 提交认证
    @PostMapping
    public ResponseDO insertAuthenticate(@RequestParam(required = false) String code, @Valid @RequestBody Authenticate authenticate) {
        Integer id = authenticateService.insertAuthenticate(code, authenticate);
        return ResponseDO.success(id);
    }
}
