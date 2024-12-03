package vip.xiaozhao.intern.baseUtil.intf.entity;

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
    @NotEmpty
    String title;
    @NotNull
    Integer type;
    @NotEmpty
    List<Topic> topics;
    String content;
    List<Integer> imageIds;
    String urls;
}
