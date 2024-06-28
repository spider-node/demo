package com.spider.demo.inventory.sdk.data;

import cn.spider.framework.annotation.StaTaskField;
import lombok.Data;

@Data
public class ReleaseStockParam {
    /**
     * 锁标识
     */
    @StaTaskField("stock.lockCode")
    private String lockCode;
}