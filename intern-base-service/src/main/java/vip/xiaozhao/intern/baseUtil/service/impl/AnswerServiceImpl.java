package vip.xiaozhao.intern.baseUtil.service.impl;

import cn.hutool.core.util.ObjUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vip.xiaozhao.intern.baseUtil.intf.constant.PageConstant;
import vip.xiaozhao.intern.baseUtil.intf.constant.RedisConstant;
import vip.xiaozhao.intern.baseUtil.intf.entity.Answer;
import vip.xiaozhao.intern.baseUtil.intf.mapper.AnswerMapper;
import vip.xiaozhao.intern.baseUtil.intf.mapper.QuestionMapper;
import vip.xiaozhao.intern.baseUtil.intf.service.AnswerService;
import vip.xiaozhao.intern.baseUtil.intf.service.UserService;
import vip.xiaozhao.intern.baseUtil.intf.utils.ConvertUtils;
import vip.xiaozhao.intern.baseUtil.intf.vo.AnswerDetailVo;
import vip.xiaozhao.intern.baseUtil.intf.vo.QuestionDetailVo;
import vip.xiaozhao.intern.baseUtil.intf.vo.UserBasicVo;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author dogofayaka
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Resource
    private AnswerMapper answerMapper;

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public List<AnswerDetailVo> listAnswers(int id, int rule, int page) {
        Object o = null;
        // 如果页码为 1 则查缓存，不存在则缓存
        String key = RedisConstant.ANSWER_DETAIL + id + ":" + rule;
        if (page == 1) {
            o = redisTemplate.opsForValue().get(key);
        }
        List<AnswerDetailVo> vos;
        int offset = (page - 1) * PageConstant.SIZE;
        // 如果查到了，直接返回
        if (o != null) {
            vos = ConvertUtils.convert2List(o);
        } else {
            // 如果为空就去数据库查
            if (rule == 1) {
                vos = answerMapper.listAnswersByIdAndHeat(id, PageConstant.SIZE, offset);
            } else {
                vos = answerMapper.listAnswersByIdAndAddTime(id, PageConstant.SIZE, offset);
            }
            if (vos == null) {
                throw new RuntimeException("问题不存在");
            }
            // 判断用户是否存在，并补充用户基本信息
            for (AnswerDetailVo vo : vos) {
                int userId = vo.getUserId();
                if (userId == 0) {
                    throw new RuntimeException("用户不存在");
                }
                UserBasicVo userBasic = userService.getUserBasic(userId);
                vo.setUserVo(userBasic);
            }
            // 第一页放缓存
            if (page == 1) {
                redisTemplate.opsForValue().set(key, vos, 5, TimeUnit.MINUTES);
            }
        }
        return vos;
    }

    @Override
    public int addAnswer(Answer answer) {
        int userId = answer.getUserId();
        int questionId = answer.getQuestionId();
        UserBasicVo userBasic = userService.getUserBasic(userId);
        // TODO 暂时先用详细的顶着
        QuestionDetailVo questionDetail = questionMapper.getQuestionDetailById(questionId);
        if (ObjUtil.isEmpty(userBasic) || ObjUtil.isEmpty(questionDetail)) {
            throw new RuntimeException("用户或问题不存在");
        }
        answerMapper.addAnswer(answer);
        int id = answer.getId();
        if (id <= 0) {
            throw new RuntimeException("发布回答失败");
        }
        return id;
    }
}
