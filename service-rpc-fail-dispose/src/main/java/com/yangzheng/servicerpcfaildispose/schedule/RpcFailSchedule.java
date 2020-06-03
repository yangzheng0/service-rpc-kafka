package com.yangzheng.servicerpcfaildispose.schedule;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.google.common.collect.Lists;
import com.yangzheng.servicerpcfaildispose.dao.MallMqLogMapper;
import com.yangzheng.servicerpcfaildispose.model.MallMqLog;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;
import java.util.List;

/**
 * @author yangzheng
 * @Description: //TODO
 * @Title: RpcFailSchedule
 * @ProjectName service-rpc-kafka
 * @date 2020/6/3/003 16:35
 */
@Component
@Slf4j
public class RpcFailSchedule {

    @Autowired
    MallMqLogMapper mallMqLogMapper;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Scheduled(cron = "* */1 * * * ?")
    public void handleFailRpc() {
        List<String> resList = Lists.newArrayList();
        Wrapper<MallMqLog> wrapper = new EntityWrapper<>();
        wrapper.and("mq_status",1);
        wrapper.le("resend_times",6);

        // 查询所有处理失败的消息
        List<MallMqLog> list = mallMqLogMapper.selectList(wrapper);
        for (MallMqLog mallMqLog : list) {
            resList.add(mallMqLog.getId() + "," + mallMqLog.getTopic() + "," + mallMqLog.getMqKey());
            Integer resendTimes = mallMqLog.getResendTimes();
            String value = mallMqLog.getMqValue();
            JSONObject json = JSONObject.fromObject(value);
            // 添加重发标志
            json.put("mq_message_resend_flag", mallMqLog.getId());
            value = json.toString();
            String key = StringUtils.isNotEmpty(mallMqLog.getMqKey()) ? mallMqLog.getMqKey() : null;
            // 重新发送处理失败的消息
            kafkaTemplate.send(mallMqLog.getTopic(), key, value)
                    .addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                        @Override
                        public void onFailure(Throwable ex) {
                            // do nothing
                        }

                        @Override
                        public void onSuccess(SendResult<String, Object> result) {
                            // 发送成功,重发次数加1
                            MallMqLog log = new MallMqLog();
                            log.setId(mallMqLog.getId());
                            Integer times = (resendTimes + 1);
                            log.setResendTimes(times);
                            log.setOpTime(new Date());
                            mallMqLogMapper.updateById(log);
                        }
                    });
        }
        log.info("rehandleFailMq:{}", resList);
    }
}
