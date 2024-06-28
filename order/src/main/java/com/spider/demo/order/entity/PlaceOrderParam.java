package com.spider.demo.order.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlaceOrderParam {
    /**
     * 商品数量
     */
    private BigDecimal goodNumber;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品编号
     */
    private String goodCode;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 下单人
     */
    private String createUser;
}
