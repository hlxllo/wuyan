package vip.xiaozhao.intern.baseUtil.intf.vo;

import lombok.Data;
import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class QuestionDetailVo {
    private int id;
    private int userId;
    private String title;
    private int type;
    private List<Topic> topics;
    private String content;
    private String urls;
    private int answers;
    private int browses;
    private Date addTime;
    private UserBasicVo userVo;
}
