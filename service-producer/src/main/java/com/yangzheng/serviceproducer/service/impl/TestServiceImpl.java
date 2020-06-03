package com.yangzheng.serviceproducer.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yangzheng.service.TestService;
import com.yangzheng.vo.UserVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangzheng
 * @Description: //TODO
 * @Title: TestServiceImpl
 * @ProjectName service-rpc-kafka
 * @date 2020/6/3/003 11:34
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {
    @Override
    public String test(UserVo userVo) {
        log.info("dubbo服务调用成功,服务调用者为"+userVo.getName());
        return "dubbo服务调用成功,服务调用者为"+userVo.getName();
    }
}
