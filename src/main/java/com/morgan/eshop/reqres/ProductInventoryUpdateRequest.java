package com.morgan.eshop.reqres;

import com.morgan.eshop.entity.ProductInventory;
import com.morgan.eshop.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description: 商品库存更新请求
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
public class ProductInventoryUpdateRequest implements Request{

    /**
     * 商品库存
     */
    private ProductInventory productInventory;
    /**
     * 库存商品服务(Service层)
     */
    private ProductInventoryService productInventoryService;
    /**
     * redis操作工具类
     */
    private RedisTemplate redisTemplate;

    public ProductInventoryUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService,
                                         RedisTemplate redisTemplate) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 库存更新请求核心处理逻辑
     */
    @Override
    public void process() {
        // 先删除缓存
        String key = "product:inventory:" + productInventory.getProductId();
        redisTemplate.opsForValue().getOperations().delete(key);
        System.out.println("内存队列删除商品库存缓存:key->" + key);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 再更新数据库
        productInventoryService.updateProductInventory(productInventory);
        System.out.println("内存队列更新数据库商品库存数量:productId->" + productInventory.getProductId());
    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }
}
