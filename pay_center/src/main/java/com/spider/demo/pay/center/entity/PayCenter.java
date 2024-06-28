package com.spider.demo.pay.center.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spider.demo.pay.center.entity.enums.PayStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 支付表
 * </p>
 *
 * @author dds
 * @since 2024-06-14
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "pay_center", autoResultMap = true)
public class PayCenter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 支付订单号
     */
    private String payOrder;

    /**
     * 第三方订单号
     */
    private String otherNo;

    /**
     * 商品编号
     */
    private String goodCode;

    /**
     * 支付金额
     */
    private BigDecimal payMoney;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 订单状态
     */
    private PayStatus orderStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 支付人
     */
    private String payUser;
}
