package vip.xiaozhao.intern.baseUtil.intf.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class User implements Serializable {
    private int id;
    private String url;
    private int follows;
    private int fans;
    private String collegeId;
    private String collegeName;
    private String major;
    private int direction;
    private String startDate;
    private String endDate;
}
