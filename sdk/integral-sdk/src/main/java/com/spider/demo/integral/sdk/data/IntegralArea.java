package com.spider.demo.integral.sdk.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegralArea {
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
