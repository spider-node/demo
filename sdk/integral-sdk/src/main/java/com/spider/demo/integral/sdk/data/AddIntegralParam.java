package com.spider.demo.integral.sdk.data;

import cn.spider.framework.annotation.StaTaskField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddIntegralParam {

    @StaTaskField("integral.integralNum")
    private BigDecimal integralNum;

    @StaTaskField("integral.user")
    private String user;
}
