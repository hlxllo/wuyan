package vip.xiaozhao.intern.baseUtil.controller.sts;

import com.aliyun.sts20150401.Client;
import com.aliyun.sts20150401.models.AssumeRoleRequest;
import com.aliyun.sts20150401.models.AssumeRoleResponse;
import com.aliyun.sts20150401.models.AssumeRoleResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaozhao.intern.baseUtil.config.StsProperties;

import static com.aliyun.teautil.Common.assertAsString;


@RestController
public class StsController {

    @Resource
    private StsProperties stsProperties;

    @Resource
    private Client stsClient;

    @GetMapping("/get_sts_token_for_oss_upload")
    public AssumeRoleResponseBody.AssumeRoleResponseBodyCredentials generateStsToken() {
        AssumeRoleRequest assumeRoleRequest = new AssumeRoleRequest()
            .setDurationSeconds(900L)
            // 将<your_role_session_name>设置为自定义的会话名称，例如 my-website-server。
            .setRoleSessionName(stsProperties.getRoleSessionName())
            // 将<your_role_arn>替换为拥有上传文件到指定OSS Bucket权限的RAM角色的ARN，可以在 RAM 角色详情中获得角色 ARN，详情参考：https://help.aliyun.com/zh/ram/user-guide/view-the-information-about-a-ram-role
            .setRoleArn(stsProperties.getRoleArn());
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            AssumeRoleResponse response = stsClient.assumeRoleWithOptions(assumeRoleRequest, runtime);
            return response.body.credentials;
        } catch (TeaException error) {
            // 如有需要，请打印 error
            assertAsString(error.message);
            return null;
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            assertAsString(error.message);
            return null;
        }
    }
}
