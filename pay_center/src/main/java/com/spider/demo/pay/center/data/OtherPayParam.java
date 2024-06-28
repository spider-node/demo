package com.spider.demo.pay.center.data;

import com.spider.demo.pay.center.entity.enums.PayStatus;
import lombok.Data;

@Data
public class OtherPayParam {
    private String orderNo;

    private PayStatus payStatus;
}
