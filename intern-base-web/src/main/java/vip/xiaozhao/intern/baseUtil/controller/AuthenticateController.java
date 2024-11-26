package vip.xiaozhao.intern.baseUtil.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseDO authenticate(@Param("code") String code, @RequestBody Authenticate authenticate) {
        authenticateService.addAuthenticate(code, authenticate);
        return ResponseDO.success(null);
    }
}
