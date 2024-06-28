package com.spider.demo.inventory.sdk.data;

import cn.spider.framework.annotation.NoticeSta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockStockRsp {
    /**
     * 锁定标识
     */
    @NoticeSta(target = "stock.lockStatus")
    private Boolean lockStatus;

    /**
     * 备注
     */
    @NoticeSta(target = "stock.remark")
    private String remark;
}
