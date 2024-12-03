package vip.xiaozhao.intern.baseUtil.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vip.xiaozhao.intern.baseUtil.intf.entity.Authenticate;
import vip.xiaozhao.intern.baseUtil.intf.enums.FirstMajorEnum;
import vip.xiaozhao.intern.baseUtil.intf.mapper.AuthenticateMapper;
import vip.xiaozhao.intern.baseUtil.intf.service.AuthenticateService;
import vip.xiaozhao.intern.baseUtil.intf.utils.StringUtil;
import vip.xiaozhao.intern.baseUtil.intf.utils.uploadUtils.Base64ImageUtils;
import vip.xiaozhao.intern.baseUtil.intf.utils.uploadUtils.UploadUtils;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Resource
    private AuthenticateMapper authenticateMapper;

    @Override
    public Integer insertAuthenticate(String code, Authenticate authenticate) {
        String type = authenticate.getType();
        String studyCard = authenticate.getStudyCard();
        // 校验方向
        int direction = Integer.parseInt(authenticate.getDirection());
        if (direction != 1 && direction != 2) {
            throw new RuntimeException("方向类型错误！");
        }
        // 邮箱或图片校验
        if (type.equals("1")) {
            // 验证邮箱
            // 对 code 做一些处理，处理完后再插入数据库
            if (StringUtil.isEmpty(code) || StringUtil.isEmpty(authenticate.getEmail())) {
                throw new RuntimeException("邮箱和验证码不能为空！");
            }
            // TODO 后续业务
        } else {
            // 验证证件照
            if (StringUtil.isEmpty(studyCard)) {
                throw new RuntimeException("证件照不能为空！");
            }
        }
        // TODO 根据 id 查询学校名称并插入
        // 插入数据
        authenticateMapper.insertAuthenticate(authenticate);
        return authenticate.getId();
    }
}
