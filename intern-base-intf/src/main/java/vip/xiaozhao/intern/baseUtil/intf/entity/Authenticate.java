package vip.xiaozhao.intern.baseUtil.intf.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class Authenticate {
    String collegeId;
    String studyCard;
    String email;
    String type;
    String major;
    String direction;
    String tutor;
    String startDate;
    String endDate;
}
