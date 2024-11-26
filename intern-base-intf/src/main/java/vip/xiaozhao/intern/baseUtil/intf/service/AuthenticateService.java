package vip.xiaozhao.intern.baseUtil.intf.service;

import vip.xiaozhao.intern.baseUtil.intf.entity.Authenticate;

public interface AuthenticateService {
    void addAuthenticate(String code, Authenticate authenticate);
}
