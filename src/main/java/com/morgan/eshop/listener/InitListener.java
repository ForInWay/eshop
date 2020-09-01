package com.morgan.eshop.listener;


import com.morgan.eshop.thread.RequestProcessorThreadPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Description: 系统初始化监听器
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
public class InitListener implements ServletContextListener{


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 初始化工作池线程和内存队列
        System.out.println("系统初始化监听器");
        RequestProcessorThreadPool.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
