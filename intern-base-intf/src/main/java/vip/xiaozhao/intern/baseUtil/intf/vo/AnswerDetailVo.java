package vip.xiaozhao.intern.baseUtil.intf.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hlxllo
 */
@Data
public class AnswerDetailVo implements Serializable {
    private int id;
    private int userId;
    private int questionId;
    private String content;
    private String urls;
    private int likes;
    private int favorites;
    private int comments;
    private Date addTime;
    private UserBasicVo userVo;
}
