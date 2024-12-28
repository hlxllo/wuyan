package vip.xiaozhao.intern.baseUtil.service.impl;

import cn.hutool.core.util.ObjUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vip.xiaozhao.intern.baseUtil.intf.mapper.UserMapper;
import vip.xiaozhao.intern.baseUtil.intf.service.UserService;
import vip.xiaozhao.intern.baseUtil.intf.utils.ConvertUtils;
import vip.xiaozhao.intern.baseUtil.intf.vo.UserBasicVo;

import java.util.concurrent.TimeUnit;

/**
 * @author dogofayaka
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public UserBasicVo getUserBasic(int id) {
        Object o = redisTemplate.opsForValue().get(id);
        UserBasicVo userVo;
        if (o == null) {
            // 如果没查到，去数据库查，并放入缓存
            userVo = userMapper.getUserBasicById(id);
            if (ObjUtil.isEmpty(userVo)) {
                throw new RuntimeException("用户不存在");
            }
            redisTemplate.opsForValue().set(id, userVo, 5, TimeUnit.MINUTES);
        } else {
            userVo = ConvertUtils.convertObject(o, UserBasicVo.class);
        }
        return userVo;
    }
}
