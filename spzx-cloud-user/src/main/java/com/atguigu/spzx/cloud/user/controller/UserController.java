package com.atguigu.spzx.cloud.user.controller;

import com.atguigu.spzx.cloud.user.entity.User;
import com.atguigu.spzx.cloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService ;
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/findUserByUserId/{userId}")
    public User findUserByUserId(@PathVariable(value = "userId") Long userId) {
        User user = userService.findUserByUserId(userId);
        System.out.println("当前处理请求的端口: " + port);
        user.setUserName(user.getUserName()+ " , port : " + port);
        return user;
    }

}