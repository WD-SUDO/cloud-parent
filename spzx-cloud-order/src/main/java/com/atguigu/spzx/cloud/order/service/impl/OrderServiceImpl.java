package com.atguigu.spzx.cloud.order.service.impl;

import com.atguigu.spzx.cloud.order.entity.Order;
import com.atguigu.spzx.cloud.order.entity.User;
import com.atguigu.spzx.cloud.order.feign.UserFeign;
import com.atguigu.spzx.cloud.order.mapper.OrderMapper;
import com.atguigu.spzx.cloud.order.service.OrderService;
import com.sun.jdi.PrimitiveValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper ;
    @Autowired
    private RestTemplate restTemplate ;

//    @Override
//    public Order findOrderByOrderId(Long orderId) {
//        Order order = orderMapper.findOrderByOrderId(orderId);
//        Long userId = order.getUserId();
////        User user = restTemplate.getForObject("http://localhost:10100/api/user/findUserByUserId/"+userId, User.class);
////        order.setUser(user);
//        User user = restTemplate.getForObject("http://spzx-cloud-user/api/user/findUserByUserId/"+userId, User.class);
//        //开启负载均衡我们不能在进行指定具体的ip和端口的调用只能调用必须通过服务名称从我们的注册中心拉去我们的服务列表进行远程的负载均衡的调用
//        order.setUser(user);
//        return order;
//    }
    @Autowired
    private UserFeign userFeign ;//远程调用在当前的服务端声明用于远程调用的 接口统一称为Feign接口
    @Override
    public Order findOrderByOrderId(Long orderId) {
        Order order = orderMapper.findOrderByOrderId(orderId);
        Long userId = order.getUserId();
        //远程的接口调用通过的是动态代理实现的
        //feign组件的连接超时时间是10秒，读取数据的超时时间是60秒
        //feign是怎么进行远程调用的？？？
        User user = userFeign.findUserByUserId(userId);
        order.setUser(user);
        return order;
    }

}