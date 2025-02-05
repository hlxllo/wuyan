package vip.xiaozhao.intern.baseUtil.intf.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hlxllo
 * @description 评论类
 * @date 2025/2/5
 */
@Data
public class Comment implements Serializable {
    private int userId;
    private int answerId;
    private int firstCommentId;
    private String content;
    //private int likes;
    //private int secondComments;
    //private Date addTime;
}
