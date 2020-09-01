package com.morgan.eshop.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 返回实体类
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    /**
     * 状态
     */
    private String status;

    /**
     * 信息
     */
    private String message;

    public Result(String status) {
        this.status = status;
    }
}
