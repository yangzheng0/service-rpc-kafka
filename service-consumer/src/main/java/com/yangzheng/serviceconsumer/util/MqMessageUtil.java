package com.yangzheng.serviceconsumer.util;

import net.sf.json.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author yudong
 * @date 2019/12/31
 */
public class MqMessageUtil {
    public static final String RESEND_FLAG = "mq_message_resend_flag";
    private static KafkaTemplate<String, Object> kafkaTemplate = SpringUtil.getApplicationContext().getBean(KafkaTemplate.class);

    public static void handleSuccessMsg(ConsumerRecord<?, ?> record) {
        JSONObject json = JSONObject.fromObject(record.value());
        if (json.has(RESEND_FLAG)) {
            int id = json.getInt(RESEND_FLAG);
            JSONObject object = new JSONObject();
            object.put("id", id);
            kafkaTemplate.send("mall_moonmall_statistical_success", object.toString());
        }
    }

    public static void handleFailMsg(ConsumerRecord<?, ?> record) {
        String key = record.key() != null ? String.valueOf(record.key()) : "";
        JSONObject json = new JSONObject();
        json.put("topic", record.topic());
        json.put("key", key);
        json.put("value", record.value());
        kafkaTemplate.send("mall_moonmall_statistical_fail", json.toString());
    }

}
