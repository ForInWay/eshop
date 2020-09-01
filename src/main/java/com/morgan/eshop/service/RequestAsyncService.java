package com.morgan.eshop.service;

import com.morgan.eshop.reqres.Request;

/**
 * @Description: 请求异步处理Service，路由功能/请求过滤
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
public interface RequestAsyncService {

    /**
     * 请求异步处理逻辑
     * @param request
     */
    void process(Request request);
}
