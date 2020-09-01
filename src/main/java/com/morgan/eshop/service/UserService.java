package com.morgan.eshop.service;

import com.morgan.eshop.entity.User;

import java.util.List;

/**
 * @Description: 用户接口
 * @Date:2020/8/4
 * @User:morgan.b.chen
 */
public interface UserService {

    /**
     * 获取用户列表
     * @return
     */
    List<User> findUserList();
}
