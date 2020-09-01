package com.morgan.eshop.controller;

import com.morgan.eshop.entity.User;
import com.morgan.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 用户控制器
 * @Date:2020/8/4
 * @User:morgan.b.chen
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping("/findUserList")
    public List<User> findUserList(){
        List<User> users = userService.findUserList();
        redisTemplate.opsForValue().set("userId",users);
        return (List<User>) redisTemplate.opsForValue().get("userId");
    }
}
