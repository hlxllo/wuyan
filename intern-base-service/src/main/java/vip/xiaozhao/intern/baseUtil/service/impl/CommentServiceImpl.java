package vip.xiaozhao.intern.baseUtil.service.impl;

import cn.hutool.core.util.ObjUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaozhao.intern.baseUtil.intf.entity.Comment;
import vip.xiaozhao.intern.baseUtil.intf.mapper.AnswerMapper;
import vip.xiaozhao.intern.baseUtil.intf.mapper.CommentMapper;
import vip.xiaozhao.intern.baseUtil.intf.service.CommentService;
import vip.xiaozhao.intern.baseUtil.intf.service.UserService;
import vip.xiaozhao.intern.baseUtil.intf.vo.AnswerDetailVo;

/**
 * @author hlxllo
 * @description 评论服务层
 * @date 2025/2/5
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserService userService;

    @Resource
    private AnswerMapper answerMapper;
    @Override
    @Transactional
    public void addComment(Comment comment) {
        // 校验
        int userId = comment.getUserId();
        int answerId = comment.getAnswerId();
        int firstCommentId = comment.getFirstCommentId();
        userService.getUserBasic(userId);
        AnswerDetailVo answer = answerMapper.getAnswerDetailById(answerId);
        if (ObjUtil.isEmpty(answer)) {
            throw new RuntimeException("回答不存在");
        }
        if (firstCommentId > 0) {
            // 添加的是二级评论
            // 先查询一级评论是否存在
            Comment firstComment = commentMapper.getCommentById(firstCommentId);
            if (ObjUtil.isEmpty(firstComment)) {
                throw new RuntimeException("一级评论不存在");
            }
            // 增加一级评论对应的二级评论数量
            commentMapper.increaseSecondCommentCountById(firstCommentId);
        }
        // 添加评论
        commentMapper.addComment(comment);
    }
}
