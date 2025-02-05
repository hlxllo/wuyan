package vip.xiaozhao.intern.baseUtil.controller.comment;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import vip.xiaozhao.intern.baseUtil.controller.BaseController;
import vip.xiaozhao.intern.baseUtil.intf.dto.ResponseDO;
import vip.xiaozhao.intern.baseUtil.intf.entity.Comment;
import vip.xiaozhao.intern.baseUtil.intf.service.CommentService;
import vip.xiaozhao.intern.baseUtil.intf.utils.StringUtil;

/**
 * @author hlxllo
 * @description 评论控制层
 * @date 2025/2/5
 */
@RestController
@RequestMapping("/wuyan/comment")
public class CommentController extends BaseController {
    @Override
    protected int getCurrentUserId(HttpServletRequest request) {
        return super.getCurrentUserId(request);
    }

    @Resource
    private CommentService commentService;

    // 发布评论
    @PostMapping
    public ResponseDO releaseComment(@RequestBody Comment comment) {
        if (comment.getUserId() <= 0 || comment.getAnswerId() <= 0 ||
                comment.getFirstCommentId() < 0 || StringUtil.isEmpty(comment.getContent())) {
            return ResponseDO.fail("参数错误");
        }
        commentService.addComment(comment);
        return ResponseDO.success(null);
    }

}
