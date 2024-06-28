package com.spider.demo.integral.sdk.data;

import cn.spider.framework.annotation.NoticeSta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateDeductionResult {

    /**
     * 抵扣金额
     */
    @NoticeSta(target = "integral.deductionAmount")
    private BigDecimal deductionAmount;

    /**
     * 抵扣后金额
     */
    @NoticeSta(target = "integral.deductionAfterAmount")
    private BigDecimal deductionAfterAmount;

    /**
     * 使用积分
     */
    @NoticeSta(target = "integral.userIntegral")
    private BigDecimal userIntegral;

    /**
     * 剩余积分
     */
    @NoticeSta(target = "integral.remainderIntegral")
    private BigDecimal remainderIntegral;



}
