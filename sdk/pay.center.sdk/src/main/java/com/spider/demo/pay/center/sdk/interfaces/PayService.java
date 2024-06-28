package com.spider.demo.pay.center.sdk.interfaces;

import cn.spider.framework.annotation.NoticeScope;
import cn.spider.framework.annotation.TaskComponent;
import cn.spider.framework.annotation.TaskService;
import cn.spider.framework.annotation.enums.ScopeTypeEnum;
import com.spider.demo.pay.center.sdk.data.*;

@TaskComponent(name = "pay_manager", workerName = "pay")
public interface PayService {
    // 创建支付单
    @TaskService(name = "create_pay_order",functionName = "创建支付订单",desc = "向支付系统发起支付申请")
    @NoticeScope(scope = {ScopeTypeEnum.STABLE}, target = "pay")
    PayCenterArea createPayCenter(CreatePayOrderParam param);
    // 查询支付单
    @TaskService(name = "select_pay_center",functionName = "查询支付订单",desc = "获取支付单的支付详情")
    @NoticeScope(scope = {ScopeTypeEnum.STABLE}, target = "pay")
    PayCenterArea selectPayCenter(SelectPayCenterParam param);
    // 发起支付
    @TaskService(name = "pay",functionName = "发起支付",desc = "向第三方发起支付")
    @NoticeScope(scope = {ScopeTypeEnum.STABLE}, target = "pay")
    PayCenterArea pay(PayParam param);
    // 修改支付单状态
    @TaskService(name = "update_status",functionName = "修改支付状态",desc = "第三方支付回调后,对支付状态进行修改")
    void updateStatus(UpdatePayOrderStatusParam param);
    // 查询支付状态信息
    @TaskService(name = "query_pay_status",functionName = "查询支付状态",desc = "用于轮询获取支付状态")
    PayStatusRsp queryPayStatus(PayStatusParam param);

}
