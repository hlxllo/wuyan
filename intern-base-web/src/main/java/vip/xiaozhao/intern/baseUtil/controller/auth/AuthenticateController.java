package vip.xiaozhao.intern.baseUtil.controller.auth;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import vip.xiaozhao.intern.baseUtil.controller.BaseController;
import vip.xiaozhao.intern.baseUtil.intf.dto.ResponseDO;
import vip.xiaozhao.intern.baseUtil.intf.entity.Authenticate;
import vip.xiaozhao.intern.baseUtil.intf.service.AuthenticateService;

@RestController
@RequestMapping("/wuyan/authenticate")
public class AuthenticateController extends BaseController {
    @Override
    protected int getCurrentUserId(HttpServletRequest request) {
        return super.getCurrentUserId(request);
    }

    @Resource
    AuthenticateService authenticateService;

    @PostMapping
    public ResponseDO authenticate(@RequestParam(required = false) String code, @Valid @RequestBody Authenticate authenticate) {
        authenticateService.addAuthenticate(code, authenticate);
        return ResponseDO.success(null);
    }
}
