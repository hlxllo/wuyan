package vip.xiaozhao.intern.baseUtil.intf.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.xiaozhao.intern.baseUtil.intf.vo.QuestionDetailVo;

@Mapper
public interface DetailMapper {

    QuestionDetailVo getQuestionDetailById(int id);
}
