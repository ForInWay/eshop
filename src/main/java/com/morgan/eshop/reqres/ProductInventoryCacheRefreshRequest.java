package com.morgan.eshop.reqres;

import com.morgan.eshop.entity.ProductInventory;
import com.morgan.eshop.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description: 商品库存更新缓存请求
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
public class ProductInventoryCacheRefreshRequest implements Request{

    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 库存商品服务
     */
    private ProductInventoryService productInventoryService;
    /**
     * redis操作工作类
     */
    private RedisTemplate redisTemplate;

    public ProductInventoryCacheRefreshRequest(Integer productId, ProductInventoryService productInventoryService,RedisTemplate redisTemplate) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 核心处理逻辑
     */
    @Override
    public void process() {
        // 数据库中查询最新商品库存数量
        ProductInventory productInventory = productInventoryService.getProductInventory(productId);
        System.out.println("内存队列数据库查询商品情况:商品id->" + productInventory.getProductId() + ",商品数量->" + productInventory.getInventoryCnt());
        // 更新缓存中该商品的数量
        String key = "product:inventory:" + productInventory.getProductId();
        redisTemplate.opsForValue().set(key,String.valueOf(productInventory.getInventoryCnt()));
        System.out.println("内存队列刷新到redis缓存:key->" + key + ",商品库存->" + productInventory.getInventoryCnt());
    }

    /**
     * 获取商品id
     * @return
     */
    @Override
    public Integer getProductId() {
        return productId;
    }
}
