package vip.xiaozhao.intern.baseUtil.intf.service;

import vip.xiaozhao.intern.baseUtil.intf.entity.Authenticate;

public interface AuthenticateService {
    // 新增认证信息
    Integer insertAuthenticate(String code, Authenticate authenticate);
}
