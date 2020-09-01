package com.morgan.eshop.reqres;

/**
 * @Description: 请求接口封装
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
public interface Request {

    /**
     * 核心处理方法
     */
    void process();

    /**
     * 获取商品id
     * @return
     */
    Integer getProductId();
}
