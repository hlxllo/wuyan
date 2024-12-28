package vip.xiaozhao.intern.baseUtil.intf.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author dogofayaka
 */
@Data
public class Answer {
    private int id;
    private int userId;
    private int questionId;
    private String content;
    private String urls;
}
