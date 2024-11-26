package vip.xiaozhao.intern.baseUtil.intf.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.xiaozhao.intern.baseUtil.intf.entity.Authenticate;

@Mapper
public interface AuthenticateMapper {

    void insertAuthenticate(Authenticate authenticate);
}
