package com.spider.demo.pay.center.sdk.data;

import cn.spider.framework.annotation.StaTaskField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePayOrderParam {
    /**
     * 第三方订单号
     */
    @StaTaskField("payOder.otherNo")
    private String otherNo;

    /**
     * 商品编号
     */
    @StaTaskField("payOder.goodCode")
    private String goodCode;

    /**
     * 支付金额
     */
    @StaTaskField("payOder.payMoney")
    private BigDecimal payMoney;

    /**
     * 支付人
     */
    @StaTaskField("payOder.payUser")
    private String payUser;

    /**
     * 支付方式
     */
    @StaTaskField("payOder.payType")
    private String payType;
}
