package com.yangzheng.servicerpcfaildispose.listener;

import com.yangzheng.servicerpcfaildispose.dao.MallMqLogMapper;
import com.yangzheng.servicerpcfaildispose.model.MallMqLog;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author yangzheng
 * @Description: //TODO
 * @Title: RpcDisposeListener
 * @ProjectName service-rpc-kafka
 * @date 2020/6/3/003 15:44
 */
@Component
@Slf4j
public class RpcDisposeListener {

    @Autowired
    private MallMqLogMapper mallMqLogMapper;


    /**
     * 监听成功调用
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = "mall_moonmall_statistical_success",groupId = "test")
    public void RpcSuccess(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("mall_moonmall_statistical_success 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }
    /**
     * 监听失败调用
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = "mall_moonmall_statistical_fail",groupId = "test")
    public void RpcFail(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("mall_moonmall_statistical_fail 消费了： Topic:" + topic + ",Message:" + msg);
            JSONObject json = JSONObject.fromObject(record.value());
            String topicRpc = json.getString("topic");
            String key = json.getString("key");
            String valueStr = json.getString("value");
            JSONObject valueJson = JSONObject.fromObject(valueStr);
            if (valueJson.has("mq_message_resend_flag")) {
                return;
            }
            MallMqLog mallMqLog = new MallMqLog();
            mallMqLog.setTopic(topicRpc);
            mallMqLog.setMqKey(key);
            mallMqLog.setMqValue(valueStr);
            mallMqLog.setResendTimes(0);
            mallMqLog.setMqStatus(2);
            mallMqLogMapper.insert(mallMqLog);
            ack.acknowledge();
        }
    }
}
