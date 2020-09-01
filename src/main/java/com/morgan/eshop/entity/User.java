package com.morgan.eshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户实体类
 * @Date:2020/8/9
 * @User:morgan.b.chen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String name;
}
