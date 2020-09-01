package com.morgan.eshop.service.impl;

import com.morgan.eshop.entity.User;
import com.morgan.eshop.mapper.UserMapper;
import com.morgan.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 用户接口实现类
 * @Date:2020/8/4
 * @User:morgan.b.chen
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户列表
     * @return
     */
    @Override
    public List<User> findUserList() {
        return userMapper.findUserList();
    }
}
