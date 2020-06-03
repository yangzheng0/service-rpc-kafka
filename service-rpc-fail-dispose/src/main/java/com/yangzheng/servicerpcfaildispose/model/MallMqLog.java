package com.yangzheng.servicerpcfaildispose.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 消息处理失败记录表
 * </p>
 *
 * @author yangzheng
 * @since 2020-06-03
 */
@TableName("mall_mq_log")
public class MallMqLog extends Model<MallMqLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 主题
     */
    private String topic;

    /**
     * 消息键
     */
    @TableField("mq_key")
    private String mqKey;

    /**
     * 消息内容
     */
    @TableField("mq_value")
    private String mqValue;

    /**
     * 重发次数
     */
    @TableField("resend_times")
    private Integer resendTimes;

    /**
     * 状态,1:未完成处理,2:已完成处理
     */
    @TableField("mq_status")
    private Integer mqStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 最后操作时间
     */
    @TableField("op_time")
    private Date opTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getMqKey() {
        return mqKey;
    }

    public void setMqKey(String mqKey) {
        this.mqKey = mqKey;
    }
    public String getMqValue() {
        return mqValue;
    }

    public void setMqValue(String mqValue) {
        this.mqValue = mqValue;
    }
    public Integer getResendTimes() {
        return resendTimes;
    }

    public void setResendTimes(Integer resendTimes) {
        this.resendTimes = resendTimes;
    }
    public Integer getMqStatus() {
        return mqStatus;
    }

    public void setMqStatus(Integer mqStatus) {
        this.mqStatus = mqStatus;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MallMqLog{" +
        "id=" + id +
        ", topic=" + topic +
        ", mqKey=" + mqKey +
        ", mqValue=" + mqValue +
        ", resendTimes=" + resendTimes +
        ", mqStatus=" + mqStatus +
        ", createTime=" + createTime +
        ", opTime=" + opTime +
        "}";
    }
}
