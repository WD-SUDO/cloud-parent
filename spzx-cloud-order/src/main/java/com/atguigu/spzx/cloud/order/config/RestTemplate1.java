package com.atguigu.spzx.cloud.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//用于远程的服务调用

public class RestTemplate1 {
    @Bean
    @LoadBalanced//开启LoadBalanced的组件的负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
