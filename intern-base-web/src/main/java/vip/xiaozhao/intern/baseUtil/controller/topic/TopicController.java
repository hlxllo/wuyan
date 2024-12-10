package vip.xiaozhao.intern.baseUtil.controller.topic;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaozhao.intern.baseUtil.intf.dto.ResponseDO;
import vip.xiaozhao.intern.baseUtil.intf.entity.Topic;
import vip.xiaozhao.intern.baseUtil.intf.service.TopicService;

import java.util.List;

// 话题 controller
@RestController
@RequestMapping("/wuyan/topic")
@Validated
public class TopicController {

    @Resource
    private TopicService topicService;

    // 根据名称查询话题
    @GetMapping("")
    public ResponseDO getTopicsByName(@RequestParam String name) {
        List<Topic> topics = topicService.getTopicsByName(name);
        return ResponseDO.success(topics);
    }
}
