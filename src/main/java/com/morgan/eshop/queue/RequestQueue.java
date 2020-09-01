package com.morgan.eshop.queue;

import com.morgan.eshop.reqres.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 请求内存队列(单例模式)
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
public class RequestQueue {

    /**
     * 内存队列列表
     */
    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();
    /**
     * 标识位Map
     */
    private Map<Integer,Boolean> flagMap = new ConcurrentHashMap<>();

    /**
     * 添加一个请求队列
     * @param arrayBlockingQueue
     */
    public void addQueue(ArrayBlockingQueue<Request> arrayBlockingQueue) {
        this.queues.add(arrayBlockingQueue);
    }

    /**
     * 获取标识位Map
     * @return
     */
    public Map<Integer,Boolean> getFlagMap() {
        return flagMap;
    }

    /**
     * 返回内存队列列表的大小
     * @return
     */
    public int size() {
        return queues.size();
    }

    /**
     * 根据索引返回相应的内存队列
     * @param index
     * @return
     */
    public ArrayBlockingQueue<Request> getQueue(int index) {
        return queues.get(index);
    }

    /**
     * 静态内部类单例
     */
    private static class Singleton{

        private static RequestQueue requestQueue;

        static {
            requestQueue = new RequestQueue();
        }

        private static RequestQueue getInstance(){
            return requestQueue;
        }
    }

    /**
     * 获取请求内存队列实例
     * @return
     */
    public static RequestQueue getInstance(){
        return Singleton.getInstance();
    }
}
