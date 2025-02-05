package vip.xiaozhao.intern.baseUtil.intf.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.xiaozhao.intern.baseUtil.intf.entity.Comment;

/**
 * @author hlxllo
 * @description 评论持久层
 * @date 2025/2/5
 */
@Mapper
public interface CommentMapper {
    Comment getCommentById(int firstCommentId);

    void addComment(Comment comment);

    void increaseSecondCommentCountById(int firstCommentId);
}
