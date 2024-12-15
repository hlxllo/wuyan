package vip.xiaozhao.intern.baseUtil.intf.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Setter
public class Question {
    Integer id;
    @NotNull
    Integer userId;
    @NotEmpty
    String title;
    @NotNull
    Integer type;
    @Valid
    @NotEmpty
    List<Topic> topics;
    String content;
    List<Integer> imageIds;
    String urls;
}
