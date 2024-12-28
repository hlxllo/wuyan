package vip.xiaozhao.intern.baseUtil.intf.service;

import vip.xiaozhao.intern.baseUtil.intf.vo.UserBasicVo;

/**
 * @author dogofayaka
 */
public interface UserService {
    // 查询用户基本信息并缓存
    UserBasicVo getUserBasic(int id);
}
