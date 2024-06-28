package com.spider.demo.pay.center.sdk.data;

import cn.spider.framework.annotation.NoticeSta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayStatusRsp {
    /**
     * 支付状态
     */
    @NoticeSta(target = "pay.status")
    private String status;
}
