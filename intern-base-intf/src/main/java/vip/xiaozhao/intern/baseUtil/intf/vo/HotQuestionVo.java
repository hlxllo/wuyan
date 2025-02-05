package vip.xiaozhao.intern.baseUtil.intf.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hlxllo
 * @description 热门问题类
 * @date 2025/2/5
 */
@Data
public class HotQuestionVo implements Serializable {
    private int id;
    private String title;
    private String content;
    private int type;
    private int answers;
    private int browses;
    private Date addTime;
    private int heat;
}
