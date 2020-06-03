package com.yangzheng.serviceconsumer.controller;

import com.alibaba.fastjson.JSON;
import com.yangzheng.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzheng
 * @Description: //TODO
 * @Title: TestController
 * @ProjectName service-rpc-kafka
 * @date 2020/6/3/003 13:17
 */
@RestController
public class TestController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @RequestMapping("/test")
    public String test(){
        UserVo userVo = new UserVo();
        userVo.setName("yangzheng");
        userVo.setAge(18);
        kafkaTemplate.send("test", JSON.toJSONString(userVo, true));
        return "success";
    }
}
