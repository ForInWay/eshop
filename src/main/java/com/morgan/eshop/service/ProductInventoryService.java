package com.morgan.eshop.service;

import com.morgan.eshop.entity.ProductInventory;

/**
 * @Description: 商品库存Service接口
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
public interface ProductInventoryService {

    /**
     * 更新库存
     * @param productInventory
     */
    void updateProductInventory(ProductInventory productInventory);

    /**
     * 获取商品对应的缓存数量
     * @param productId
     * @return
     */
    ProductInventory getProductInventory(Integer productId);
}
