package com.spider.demo.order.sdk.data;

import cn.spider.framework.annotation.StaTaskField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateDeductionParam {

    @StaTaskField("goodsOrder.orderNo")
    private String orderNo;

    @StaTaskField("goodsOrder.deductionAmount")
    private BigDecimal deductionAmount;
}
