package com.atguigu.spzx.cloud.order.service.impl;

import com.atguigu.spzx.cloud.order.entity.Order;
import com.atguigu.spzx.cloud.order.entity.User;
import com.atguigu.spzx.cloud.order.mapper.OrderMapper;
import com.atguigu.spzx.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper ;
    @Autowired
    private RestTemplate restTemplate ;
    @Autowired
    private DiscoveryClient discoveryClient ;

    @Override
    public Order findOrderByOrderId(Long orderId) {
        Order order = orderMapper.findOrderByOrderId(orderId);
        Long userId = order.getUserId();

        // 从 Nacos 手动获取 spzx-cloud-user 服务的实例
        List<ServiceInstance> instances = discoveryClient.getInstances("spzx-cloud-user");
        if (instances.isEmpty()) {
            throw new RuntimeException("未找到 spzx-cloud-user 服务实例");
        }
        ServiceInstance instance = instances.get(0);
        String url = "http://" + instance.getHost() + ":" + instance.getPort()
                + "/api/user/findUserByUserId/" + userId;

        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);
        return order;
    }

}