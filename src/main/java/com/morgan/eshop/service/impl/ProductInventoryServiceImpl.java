package com.morgan.eshop.service.impl;

import com.morgan.eshop.entity.ProductInventory;
import com.morgan.eshop.mapper.ProductInventoryMapper;
import com.morgan.eshop.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description: 商品库存Service实现
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService{

    @Autowired
    private ProductInventoryMapper productInventoryMapper;

    /**
     * 更新库存
     * @param productInventory
     */
    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryMapper.updateProductInventory(productInventory);
    }

    /**
     * 获取商品对应的库存数量
     * @param productId
     * @return
     */
    @Override
    public ProductInventory getProductInventory(Integer productId) {
        return productInventoryMapper.getProductInventory(productId);
    }
}
