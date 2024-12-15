package vip.xiaozhao.intern.baseUtil.intf.vo;

import lombok.Data;

import java.io.Serializable;

@Data
// 用户基本信息，缓存
public class UserBasicVo implements Serializable {
    private int id;
    private String collegeId;
    private String collegeName;
    // 关注数
    private int follows;
    // 粉丝数
    private int fans;
}
