package com.spider.demo.integral.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 积分表
 * </p>
 *
 * @author dds
 * @since 2024-06-17
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "integral", autoResultMap = true)
public class Integral {

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 积分编号
     */
    private String integralCode;

    /**
     * 积分数量
     */
    private BigDecimal integralNum;

    /**
     * 积分持有人
     */
    private String user;

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
}
