package com.yangzheng.serviceconsumer.listener;

import cn.hutool.json.JSONUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.yangzheng.service.TestService;
import com.yangzheng.serviceconsumer.util.MqMessageUtil;
import com.yangzheng.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author yangzheng
 * @Description: //TODO
 * @Title: KafkaTestListener
 * @ProjectName service-rpc-kafka
 * @date 2020/6/3/003 13:51
 */
@Component
@Slf4j
public class KafkaTestListener {


    @Reference(check=false, timeout = 60000)
    TestService testService;


    @KafkaListener(topics = "test",groupId = "test")
    public void topic_test(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            JSONObject json = JSONObject.fromObject(record.value());
            log.info("test 消费了： Topic:" + topic + ",Message:" + json);
            try {
                UserVo userVo = JSONUtil.toBean(json.toString(), UserVo.class);
                String result = testService.test(userVo);
                if (!StringUtils.isEmpty(result)) {
                    MqMessageUtil.handleSuccessMsg(record);
                } else {
                    MqMessageUtil.handleFailMsg(record);
                }
            } catch (Exception e) {
                MqMessageUtil.handleFailMsg(record);
                log.error("",e);
            }
            ack.acknowledge();
        }
    }
}
