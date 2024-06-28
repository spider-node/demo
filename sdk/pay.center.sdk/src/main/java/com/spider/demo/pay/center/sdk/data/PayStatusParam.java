package com.spider.demo.pay.center.sdk.data;

import cn.spider.framework.annotation.StaTaskField;
import lombok.Data;

@Data
public class PayStatusParam {
    /**
     * 第三方订单号
     */
    @StaTaskField("payOder.otherNo")
    private String otherNo;
}
