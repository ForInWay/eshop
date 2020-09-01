package com.morgan.eshop.mapper;

import com.morgan.eshop.entity.ProductInventory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 商品库存更新
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
@Mapper
public interface ProductInventoryMapper {

    /**
     * 更新商品库存
     * @param productInventory
     */
    void updateProductInventory(ProductInventory productInventory);

    /**
     * 获取商品对应的库存数量
     * @param productId
     * @return
     */
    ProductInventory getProductInventory(Integer productId);
}
