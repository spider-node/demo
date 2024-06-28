package com.spider.demo.inventory.sdk.interfaces;
import cn.spider.framework.annotation.NoticeScope;
import cn.spider.framework.annotation.TaskComponent;
import cn.spider.framework.annotation.TaskService;
import cn.spider.framework.annotation.enums.ScopeTypeEnum;
import com.spider.demo.inventory.sdk.data.*;

/**
 * 库存能力管理
 */
@TaskComponent(name = "stock_manager", workerName = "stock")
public interface StockService {
    // 锁库存
    @TaskService(name = "lock_stock",functionName = "锁库存",desc = "根据商品编号进行锁库存")
    LockStockRsp lockStock(LockStockParam param);
    // 扣库存
    @TaskService(name = "deduct_stock",functionName = "扣减库存",desc = "基于锁定的编号进行库存扣减")
    @NoticeScope(scope = {ScopeTypeEnum.STABLE}, target = "stock")
    StockArea deductStock(DeductStockParam param);
    // 释放库存
    @TaskService(name = "release_stock",functionName = "解锁库存",desc = "解锁库存")
    @NoticeScope(scope = {ScopeTypeEnum.STABLE}, target = "stock")
    StockArea releaseStock(ReleaseStockParam param);
}
