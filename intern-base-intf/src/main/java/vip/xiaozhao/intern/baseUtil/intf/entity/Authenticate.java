package vip.xiaozhao.intern.baseUtil.intf.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class Authenticate {
    Integer id;
    @NotEmpty
    String collegeId;
    String collegeName;
    String studyCard;
    String email;
    @NotEmpty
    String type;
    @NotEmpty
    String major;
    @NotEmpty
    String direction;
    String tutor;
    @NotEmpty
    String startDate;
    @NotEmpty
    String endDate;
}
