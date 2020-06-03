package com.yangzheng.servicerpcfaildispose.service.impl;

import com.yangzheng.servicerpcfaildispose.model.MallMqLog;
import com.yangzheng.servicerpcfaildispose.dao.MallMqLogMapper;
import com.yangzheng.servicerpcfaildispose.service.IMallMqLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息处理失败记录表 服务实现类
 * </p>
 *
 * @author yangzheng
 * @since 2020-06-03
 */
@Service
public class MallMqLogServiceImpl extends ServiceImpl<MallMqLogMapper, MallMqLog> implements IMallMqLogService {

}
