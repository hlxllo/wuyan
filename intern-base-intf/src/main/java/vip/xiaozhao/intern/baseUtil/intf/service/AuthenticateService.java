package vip.xiaozhao.intern.baseUtil.intf.service;

import vip.xiaozhao.intern.baseUtil.intf.entity.Authenticate;

public interface AuthenticateService {
    Integer insertAuthenticate(String code, Authenticate authenticate);
}
