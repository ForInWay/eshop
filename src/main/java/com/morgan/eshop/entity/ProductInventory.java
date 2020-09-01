package com.morgan.eshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 商品库存实体类
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductInventory {

    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 库存数量
     */
    private Long inventoryCnt;
}
