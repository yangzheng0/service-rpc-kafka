package com.yangzheng.serviceproducer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class ServiceProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProducerApplication.class, args);
        System.out.println("ServiceProducerApplication ====> 启动完成");
    }

}
