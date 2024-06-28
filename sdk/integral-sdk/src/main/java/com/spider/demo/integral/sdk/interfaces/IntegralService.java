package com.spider.demo.integral.sdk.interfaces;

import cn.spider.framework.annotation.NoticeScope;
import cn.spider.framework.annotation.TaskComponent;
import cn.spider.framework.annotation.TaskService;
import cn.spider.framework.annotation.enums.ScopeTypeEnum;
import com.spider.demo.integral.sdk.data.*;

@TaskComponent(name = "integral_manager", workerName = "integral")
public interface IntegralService {
    // 计算抵扣
    @TaskService(name = "calculate_deduction",functionName = "计算抵扣",desc = "提供计算可抵扣金额")
    CalculateDeductionResult calculateDeduction(CalculateDeductionParam param);
    // 锁定积分

    @TaskService(name = "lock_integral",functionName = "锁定积分兑换",desc = "锁定可兑换的积分")
    @NoticeScope(scope = {ScopeTypeEnum.STABLE}, target = "integral")
    IntegralArea lockIntegral(LockIntegralParam param);
    // 扣减积分
    @TaskService(name = "deduction_integral",functionName = "扣减积分",desc = "根据锁标识进行对积分扣减")
    @NoticeScope(scope = {ScopeTypeEnum.STABLE}, target = "integral")
    IntegralArea deductionIntegral(DeductionIntegralParam param);
    // 释放积分
    @TaskService(name = "release_integral",functionName = "释放锁定的积分",desc = "根据锁标识进行积分释放")
    @NoticeScope(scope = {ScopeTypeEnum.STABLE}, target = "integral")
    IntegralArea releaseIntegral(ReleaseIntegralParam param);
    // 增加积分
    @TaskService(name = "add_integral",functionName = "增加积分",desc = "对用户进行积分新增")
    @NoticeScope(scope = {ScopeTypeEnum.STABLE}, target = "integral")
    IntegralArea addIntegral(AddIntegralParam param);
}
