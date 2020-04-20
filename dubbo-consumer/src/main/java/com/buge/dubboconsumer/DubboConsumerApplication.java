package com.buge.dubboconsumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.buge.dubboapi.starter.annotation.EnableBuge;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

//@EnableAsync
@EnableBuge
//@EnableDubbo
@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.buge.dubboconsumer",exclude = DataSourceAutoConfiguration.class)
//@ImportResource(value = "classpath:dubbo-consumer.xml")
public class DubboConsumerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }
    /*以war包形式 启动服务*/
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.out.println("=================================");
        return builder.sources(DubboConsumerApplication.class);
    }
}
