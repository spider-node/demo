package com.spider.demo.integral.sdk.data;


import cn.spider.framework.annotation.StaTaskField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LockIntegralParam {
    /**
     * 锁编号
     */
    @StaTaskField("integral.lockCode")
    private String lockCode;

    /**
     * 锁定积分
     */
    @StaTaskField("integral.lockNumber")
    private BigDecimal integral;

    @StaTaskField("integral.user")
    private String user;

}
