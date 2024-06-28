package com.spider.demo.order.sdk.data;

import cn.spider.framework.annotation.StaTaskField;
import lombok.Data;

@Data
public class UpdateOrderInfoParam {
    /**
     * 订单编号
     */
    @StaTaskField("goodsOrder.orderNo")
    private String orderNo;

    /**
     * 订单状态
     */
    @StaTaskField("goodsOrder.orderStatus")
    private String orderStatus;

    /**
     * 备注
     */
    @StaTaskField("goodsOrder.remarks")
    private String remarks;
}
