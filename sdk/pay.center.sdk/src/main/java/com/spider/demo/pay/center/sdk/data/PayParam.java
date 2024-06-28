package com.spider.demo.pay.center.sdk.data;

import cn.spider.framework.annotation.StaTaskField;
import lombok.Data;

/**
 * 基于第三方配置
 */
@Data
public class PayParam {
    /**
     * 第三方订单号
     */
    @StaTaskField("payOder.otherNo")
    private String otherNo;

    @StaTaskField("payOder.otherNo")
    private String payStatus;

    /**
     * 支付方式
     */
    @StaTaskField("pay.payType")
    private String payType;
}
