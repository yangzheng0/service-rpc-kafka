package com.yangzheng.servicerpcfaildispose;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDubbo
@SpringBootApplication
@MapperScan("com.yangzheng.servicerpcfaildispose.dao")
@EnableScheduling
public class ServiceRpcFailDisposeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRpcFailDisposeApplication.class, args);
        System.out.println("ServiceRpcFailDisposeApplication ====> 启动完成");
    }

}
