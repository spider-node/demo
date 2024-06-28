package com.spider.demo.pay.center.sdk.data;
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
public class PayCenterArea implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
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
    private String orderStatus;

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

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(String payOrder) {
        this.payOrder = payOrder;
    }

    public String getOtherNo() {
        return otherNo;
    }

    public void setOtherNo(String otherNo) {
        this.otherNo = otherNo;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PayCenter{" +
            "id = " + id +
            ", payOrder = " + payOrder +
            ", otherNo = " + otherNo +
            ", goodCode = " + goodCode +
            ", payMoney = " + payMoney +
            ", payType = " + payType +
            ", orderStatus = " + orderStatus +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
        "}";
    }
}
