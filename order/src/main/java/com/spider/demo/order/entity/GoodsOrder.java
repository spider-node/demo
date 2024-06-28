package com.spider.demo.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spider.demo.order.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "goods_order", autoResultMap = true)
public class GoodsOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单金额
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
    private OrderStatus orderStatus;

    /**
     * 抵扣金额
     */
    private BigDecimal deductionAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal actualPayment;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 下单人
     */
    private String createUser;
}
