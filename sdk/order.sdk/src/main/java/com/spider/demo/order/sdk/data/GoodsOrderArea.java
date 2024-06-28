package com.spider.demo.order.sdk.data;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 * @author dds
 * @since 2024-06-14
 */
@Data
public class GoodsOrderArea {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 商品数量
     */
    private BigDecimal goodNumber;

    /**
     * 商品编号
     */
    private String goodCode;

    /**
     * 订单金额
     */
    private BigDecimal orderMoney;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 抵扣金额
     */
    private BigDecimal deductionAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal actualPayment;

    /**
     * 下单人
     */
    private String createUser;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
