package com.atguigu.spzx.cloud.user.service;

import com.atguigu.spzx.cloud.user.entity.User;

public interface UserService {

    // 根据用户的id查询用户详情
    User findUserByUserId(Long userId) ;

}