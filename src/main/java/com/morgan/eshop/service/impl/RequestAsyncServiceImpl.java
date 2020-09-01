package com.morgan.eshop.service.impl;

import com.morgan.eshop.queue.RequestQueue;
import com.morgan.eshop.reqres.ProductInventoryCacheRefreshRequest;
import com.morgan.eshop.reqres.ProductInventoryUpdateRequest;
import com.morgan.eshop.reqres.Request;
import com.morgan.eshop.service.RequestAsyncService;
import com.morgan.eshop.utils.HashUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Description: 请求异步处理Service，路由功能/请求过滤
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
@Service
public class RequestAsyncServiceImpl implements RequestAsyncService{

    /**
     * 请求异步处理逻辑
     * @param request
     */
    @Override
    public void process(Request request) {
        // 先做读请求的去重
        RequestQueue requestQueue = RequestQueue.getInstance();
        // 获取标识位Map
        Map<Integer,Boolean> flagMap = requestQueue.getFlagMap();
        // 判断更新请求还是读请求
        if (request instanceof ProductInventoryUpdateRequest){
            // 如果是更新库存请求，直接把商品id对应的标志位置为true
            flagMap.put(request.getProductId(),true);
        }else if (request instanceof ProductInventoryCacheRefreshRequest){
            // 获取商品对应的标志位
            Boolean flag = flagMap.get(request.getProductId());
            // 如果为flag为空，则表示还没有此商品id的更新/读请求，把商品id对应的标志位置为false
            if (flag == null){
                flagMap.put(request.getProductId(),false);
            }
            // 如果flag不为空且flag为true，则表示前面有更新库存请求或者更新/读都存在,但更新在后面，此时把商品id对应的标志位置为false
            if (flag != null && flag){
                flagMap.put(request.getProductId(),false);
            }
            // 如果flag不为空且flag为false,则表示前面已经存在同样的读请求，不需要再把此读请求放入内存队列中去，直接返回即可
            if (flag != null && !flag){
                return;
            }
        }
        // 根据不同的商品id，把请求路由到不同的内存队列当中去
        ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());
        try {
            // 把请求放入队列中，完成路由操作
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据商品id不同，路由到不同的内存队列
     * @param productId
     * @return
     */
    private ArrayBlockingQueue getRoutingQueue(Integer productId) {
        // 先获取请求内存队列池
        RequestQueue requestQueue = RequestQueue.getInstance();
        // 取商品哈希值
        int hash = HashUtils.hash(String.valueOf(productId));
        // 采用位运算对内存队列取模,结果一定在0-requestQueue.size()-1之间
        int index = (requestQueue.size() - 1) & hash;
        // 返回相应的内存队列
        return requestQueue.getQueue(index);
    }
}
