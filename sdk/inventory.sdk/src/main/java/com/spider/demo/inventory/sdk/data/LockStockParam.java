package com.spider.demo.inventory.sdk.data;

import cn.spider.framework.annotation.StaTaskField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LockStockParam {
    /**
     * 商品code
     */
    @StaTaskField("stock.goodsCode")
    private String goodsCode;

    /**
     * 商品名称
     */
    @StaTaskField("stock.goodName")
    private String goodName;

    /**
     * 锁定数量
     */
    @StaTaskField("stock.lockNumber")
    private BigDecimal lockNumber;

    /**
     * 锁标识
     */
    @StaTaskField("stock.lockCode")
    private String lockCode;
}
