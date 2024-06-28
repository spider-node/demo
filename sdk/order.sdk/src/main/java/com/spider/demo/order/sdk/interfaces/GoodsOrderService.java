package com.spider.demo.order.sdk.interfaces;

import cn.spider.framework.annotation.NoticeScope;
import cn.spider.framework.annotation.TaskComponent;
import cn.spider.framework.annotation.TaskService;
import cn.spider.framework.annotation.enums.ScopeTypeEnum;
import com.spider.demo.order.sdk.data.CreateGoodsOrderParam;
import com.spider.demo.order.sdk.data.GoodsOrderArea;
import com.spider.demo.order.sdk.data.UpdateDeductionParam;
import com.spider.demo.order.sdk.data.UpdateOrderInfoParam;

@TaskComponent(name = "goods_order_manager", workerName = "order")
public interface GoodsOrderService {
    /**
     * 创建订单
     * @param param
     * @return GoodsOrder
     */
    @TaskService(name = "create_order",functionName = "创建商品订单信息",desc = "在商品页面进行下单")
    @NoticeScope(scope = {ScopeTypeEnum.STABLE}, target = "goodsOrder")
    GoodsOrderArea createOrder(CreateGoodsOrderParam param);

    /**
     * 修改订单信息
     * @param param
     * @return
     */
    @TaskService(name = "update_order_info",functionName = "修改商品订单信息",desc = "修改商品的状态信息")
    @NoticeScope(scope = {ScopeTypeEnum.STABLE}, target = "goodsOrder")
    GoodsOrderArea updateOrderInfo(UpdateOrderInfoParam param);

    /**
     * 根据积分兑换重置支付金额
     */
    @TaskService(name = "update_deduction_info",functionName = "修改抵扣金额",desc = "修改抵扣金额,重置支付金额")
    @NoticeScope(scope = {ScopeTypeEnum.STABLE}, target = "goodsOrder")
    GoodsOrderArea updateDeduction(UpdateDeductionParam param);
}
