package vip.xiaozhao.intern.baseUtil.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.xiaozhao.intern.baseUtil.intf.entity.Authenticate;
import vip.xiaozhao.intern.baseUtil.intf.mapper.AuthenticateMapper;
import vip.xiaozhao.intern.baseUtil.intf.service.AuthenticateService;
import vip.xiaozhao.intern.baseUtil.intf.utils.StringUtil;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Resource
    AuthenticateMapper authenticateMapper;
    @Override
    public void addAuthenticate(String code, Authenticate authenticate) {
        if (StringUtil.isEmpty(authenticate.getCollegeId())) {
            throw new RuntimeException("学校id不能为空！");
        }
        if (StringUtil.isEmpty(authenticate.getMajor())) {
            throw new RuntimeException("专业不能为空！");
        }
        if (StringUtil.isEmpty(authenticate.getDirection())) {
            throw new RuntimeException("方向不能为空！");
        }
        if (StringUtil.isEmpty(authenticate.getStartDate())) {
            throw new RuntimeException("入校时间不能为空！");
        }
        if (StringUtil.isEmpty(authenticate.getEndDate())) {
            throw new RuntimeException("离校时间不能为空！");
        }
        String type = authenticate.getType();
        if (StringUtil.isEmpty(type)) {
            throw new RuntimeException("类型不能为空！");
        }
        if (type.equals("1")) {
            // 验证邮箱
            // 对 code 做一些处理，处理完后再插入数据库
            if (StringUtil.isEmpty(code)) {
                throw new RuntimeException("验证码不能为空！");
            }
            if (StringUtil.isEmpty(authenticate.getEmail())) {
                throw new RuntimeException("邮箱不能为空！");
            }
            // 后续业务
        } else {
            if (StringUtil.isEmpty(authenticate.getStudyCard())) {
                throw new RuntimeException("证件照不能为空！");
            }
            // 验证证件照
        }
        authenticateMapper.insertAuthenticate(authenticate);
    }
}
