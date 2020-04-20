package com.buge.dubboapi.starter;

import com.buge.dubboapi.starter.controller.BugeController;
import com.buge.dubboapi.starter.service.Buge;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author: yachen.shen
 * @Date 2020/2/19 17:45
 */
@ConditionalOnBean(Buge.class)
@Configuration
@EnableWebMvc
//@EnableDiscoveryClient
public class Config {

    @Bean
    public BugeController controller() {
        return new BugeController();
    }

    @Bean
    //@LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
