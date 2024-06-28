package com.spider.demo.order.sdk.data;

import cn.spider.framework.annotation.StaTaskField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateGoodsOrderParam {
    /**
     * 商品数量
     */
    @StaTaskField("goodsOrder.goodNumber")
    private BigDecimal goodNumber;

    /**
     * 商品价格
     */
    @StaTaskField("goodsOrder.price")
    private BigDecimal price;

    /**
     * 商品编号
     */
    @StaTaskField("goodsOrder.goodCode")
    private String goodCode;

    /**
     * 订单状态
     */
    @StaTaskField("goodsOrder.orderStatus")
    private String orderStatus;

    /**
     * 下单人
     */
    @StaTaskField("goodsOrder.createUser")
    private String createUser;
}
