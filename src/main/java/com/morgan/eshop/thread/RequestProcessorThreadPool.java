package com.morgan.eshop.thread;

import com.morgan.eshop.queue.RequestQueue;
import com.morgan.eshop.reqres.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 请求处理线程池：单例
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
public class RequestProcessorThreadPool {

    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public RequestProcessorThreadPool() {
        System.out.println("初始化请求处理线程池");
        // 获取请求内存队列实例
        RequestQueue requestQueue = RequestQueue.getInstance();
        // 硬编码方式构造10个线程队列，并塞到线程池当中去
        for (int i=0; i<10; i++){
            ArrayBlockingQueue<Request> arrayBlockingQueue = new ArrayBlockingQueue<>(100);
            // 把内存队列放入内存队列池
            requestQueue.addQueue(arrayBlockingQueue);
            // 对应工作线程与内存队列
            threadPool.submit(new RequestProcessorThread(arrayBlockingQueue));
        }
    }

    /**
     * 实现单例模式线程安全初始化方法多种，这里采用静态内部类的方式初始化，保证最多只会被初始化一次
     */
    private static class Singleton{

        private static RequestProcessorThreadPool requestProcessorThreadPool;

        static {
            requestProcessorThreadPool = new RequestProcessorThreadPool();
        }

        public static RequestProcessorThreadPool getInstance(){
            return requestProcessorThreadPool;
        }
    }

    /**
     * 获取线程池实例
     * @return
     */
    public static RequestProcessorThreadPool getInstance(){
        return Singleton.getInstance();
    }

    /**
     * 初始化线程池及内存队列方法
     */
    public static void init(){
        getInstance();
    }
}
