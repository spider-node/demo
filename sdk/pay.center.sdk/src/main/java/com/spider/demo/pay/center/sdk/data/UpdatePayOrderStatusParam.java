package com.spider.demo.pay.center.sdk.data;

import cn.spider.framework.annotation.StaTaskField;
import lombok.Data;

/**
 * 更新支付订单状态参数
 */
@Data
public class UpdatePayOrderStatusParam {
    /**
     * 订单状态
     */
    @StaTaskField("pay.orderStatus")
    private String orderStatus;

    /**
     * 第三方订单号
     */
    @StaTaskField("pay.otherNo")
    private String otherNo;
}
