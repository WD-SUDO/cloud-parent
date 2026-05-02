package com.atguigu.spzx.cloud.order;

import com.atguigu.spzx.cloud.order.config.DefaultLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//这个包下的所有类都开启日志记录
@EnableFeignClients(value = "com.atguigu.spzx.cloud.order.feign", defaultConfiguration = DefaultLogger.class)//开启OpenFeign，扫描@FeignClient接口
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class , args) ;
    }

}