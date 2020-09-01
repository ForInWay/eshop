package com.morgan.eshop.mapper;

import com.morgan.eshop.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 用户Mapper接口
 * @Date:2020/8/9
 * @User:morgan.b.chen
 */
@Repository
public interface UserMapper {

    /**
     * 获取用户列表
     * @return
     */
    List<User> findUserList();
}
