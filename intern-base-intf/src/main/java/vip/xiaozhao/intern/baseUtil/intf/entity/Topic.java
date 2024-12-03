package vip.xiaozhao.intern.baseUtil.intf.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class Topic {
    Integer id;
    String url;
    @NotEmpty
    String name;
    Integer follows;
    Integer questions;
}
