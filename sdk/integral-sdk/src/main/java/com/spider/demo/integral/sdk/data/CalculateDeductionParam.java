package com.spider.demo.integral.sdk.data;

import cn.spider.framework.annotation.StaTaskField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 计算折扣的请求类
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateDeductionParam {

    @StaTaskField("integral.user")
    private String user;

    @StaTaskField("integral.amount")
    private BigDecimal amount;
}
