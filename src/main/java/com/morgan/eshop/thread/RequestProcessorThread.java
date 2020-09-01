package com.morgan.eshop.thread;

import com.morgan.eshop.reqres.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @Description: 请求处理工作线程，一个线程对应一个工作队列
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
public class RequestProcessorThread implements Callable<Boolean>{

    /**
     * 线程监控的内存队列
     */
    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    /**
     * 核心逻辑处理方法
     * @return
     * @throws Exception
     */
    @Override
    public Boolean call() throws Exception {
        try {
            while (true){
                // 拿出队列中的请求任务
                Request request = queue.take();
                // 执行请求核心逻辑
                request.process();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
