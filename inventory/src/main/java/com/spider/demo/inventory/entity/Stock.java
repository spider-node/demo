package com.spider.demo.inventory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 库存表
 * </p>
 *
 * @author dds
 * @since 2024-06-14
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "stock", autoResultMap = true)
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品编号
     */
    private String goodCode;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品数量
     */
    private BigDecimal goodNumber;

    /**
     * 是否锁住
     */
    private String lockStatus;

    /**
     * 锁标识
     */
    private String lockCode;

    /**
     * 锁定数量
     */
    private BigDecimal lockNumber;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public BigDecimal getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(BigDecimal goodNumber) {
        this.goodNumber = goodNumber;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getLockCode() {
        return lockCode;
    }

    public void setLockCode(String lockCode) {
        this.lockCode = lockCode;
    }

    public BigDecimal getLockNumber() {
        return lockNumber;
    }

    public void setLockNumber(BigDecimal lockNumber) {
        this.lockNumber = lockNumber;
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
}
