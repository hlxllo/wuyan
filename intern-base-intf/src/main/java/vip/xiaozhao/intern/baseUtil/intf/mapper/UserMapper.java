package vip.xiaozhao.intern.baseUtil.intf.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.xiaozhao.intern.baseUtil.intf.entity.User;
import vip.xiaozhao.intern.baseUtil.intf.vo.UserBasicVo;

@Mapper
public interface UserMapper {

    UserBasicVo getUserBasicById(int id);

    User getUserById(Integer id);
}
