package com.morgan.eshop.controller;

import com.morgan.eshop.entity.ProductInventory;
import com.morgan.eshop.entity.base.Result;
import com.morgan.eshop.reqres.ProductInventoryCacheRefreshRequest;
import com.morgan.eshop.reqres.ProductInventoryUpdateRequest;
import com.morgan.eshop.reqres.Request;
import com.morgan.eshop.service.ProductInventoryService;
import com.morgan.eshop.service.RequestAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 商品库存控制器
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
@RestController
@RequestMapping("/productInventory")
public class ProductInventoryController {

    @Autowired
    private ProductInventoryService productInventoryService;
    @Autowired
    private RequestAsyncService requestAsyncService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 更新商品库存
     * @param productInventory
     * @return
     */
    @GetMapping("updateProductInventory")
    public Result updateProductInventory(ProductInventory productInventory){

        Result result = null;
        // 构造更新库存请求
        Request request = new ProductInventoryUpdateRequest(productInventory,productInventoryService,redisTemplate);
        // 请求路由处理
        requestAsyncService.process(request);
        result = new Result(Result.SUCCESS);
        return result;
    }

    /**
     * 获取商品库存
     * @param productId
     * @return
     */
    @GetMapping("getProductInventory")
    public ProductInventory getProductInventory(Integer productId){
        System.out.println("获取商品库存:请求参数->" + productId);
        // 构造请求对象
        Request request = new ProductInventoryCacheRefreshRequest(productId,productInventoryService,redisTemplate);
        // 放到内存队列中等待工作线程处理
        requestAsyncService.process(request);
        ProductInventory productInventory = null;
        // 把请求放到内存队列中去了之后，还要等待一会，去等待前面有商品更新的操作，同时缓存刷新的操作
        long startTime = System.currentTimeMillis();
        long endTime = 0L;
        long waitTime = 0L;
        try {
            while (true){
                // 一般公司业务，最多等待几百毫秒
                if (waitTime > 5000){
                    break;
                }
                // 尝试去缓存中读取商品数据
                String key = "product:inventory:" + productId;
                String result = (String) redisTemplate.opsForValue().get(key);
                if (result!=null && !"".equals(result)){
                    // 读取到结果则直接返回
                    productInventory = new ProductInventory(productId,Long.valueOf(result));
                    System.out.println("获取商品库存:返回结果->" + productInventory.getInventoryCnt());
                    return productInventory;
                }else{
                    // 未读取到结果，则睡眠一段时间
                    Thread.sleep(20);
                    endTime = System.currentTimeMillis();
                    waitTime = endTime - startTime;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 直接从数据库读取数据
        productInventory = productInventoryService.getProductInventory(productId);
        // 存在则并刷新缓存
        if (productInventory != null){
            String key = "product:inventory:" + productInventory.getProductId();
            redisTemplate.opsForValue().set(key,String.valueOf(productInventory.getInventoryCnt()));
            System.out.println("手动刷新redis缓存:key->" + key + ",商品数量->" + productInventory.getInventoryCnt());
            return productInventory;
        }
        return new ProductInventory(productId,-1L);
    }
}
