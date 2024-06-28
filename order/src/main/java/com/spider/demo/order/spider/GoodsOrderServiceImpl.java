package com.spider.demo.order.spider;

import com.spider.demo.order.entity.GoodsOrder;
import com.spider.demo.order.sdk.data.CreateGoodsOrderParam;
import com.spider.demo.order.sdk.data.GoodsOrderArea;
import com.spider.demo.order.sdk.data.UpdateDeductionParam;
import com.spider.demo.order.sdk.data.UpdateOrderInfoParam;
import com.spider.demo.order.sdk.interfaces.GoodsOrderService;
import com.spider.demo.order.service.IGoodsOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.UUID;

@Component("goods_order_manager")
public class GoodsOrderServiceImpl implements GoodsOrderService {

    @Resource
    private IGoodsOrderService iGoodsOrderService;

    @Override
    public GoodsOrderArea createOrder(CreateGoodsOrderParam param) {
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setGoodCode(param.getGoodCode());
        goodsOrder.setOrderNo(UUID.randomUUID().toString());
        goodsOrder.setOrderMoney(param.getGoodNumber().multiply(param.getPrice()));
        goodsOrder.setGoodNumber(param.getGoodNumber());
        goodsOrder.setCreateUser(param.getCreateUser());
        iGoodsOrderService.save(goodsOrder);
        GoodsOrderArea goodsOrderArea = new GoodsOrderArea();
        BeanUtils.copyProperties(goodsOrder, goodsOrderArea);
        return goodsOrderArea;
    }

    @Override
    public GoodsOrderArea updateOrderInfo(UpdateOrderInfoParam param) {
        iGoodsOrderService.lambdaUpdate()
                .set(GoodsOrder :: getOrderStatus,param.getOrderStatus())
                .set(StringUtils.isNotEmpty(param.getRemarks()),GoodsOrder :: getRemarks,param.getRemarks())
                .eq(GoodsOrder :: getOrderNo,param.getOrderNo()).update();
        GoodsOrder goodsOrder = iGoodsOrderService.lambdaQuery().eq(GoodsOrder::getOrderNo,param.getOrderNo()).one();
        GoodsOrderArea goodsOrderArea = new GoodsOrderArea();
        BeanUtils.copyProperties(goodsOrder, goodsOrderArea);
        return goodsOrderArea;
    }

    @Override
    public GoodsOrderArea updateDeduction(UpdateDeductionParam param) {
        GoodsOrder goodsOrder = iGoodsOrderService.lambdaQuery().eq(GoodsOrder :: getOrderNo,param.getOrderNo()).one();
        goodsOrder.setDeductionAmount(param.getDeductionAmount());
        goodsOrder.setActualPayment(goodsOrder.getOrderMoney().subtract(param.getDeductionAmount()));
        iGoodsOrderService.updateById(goodsOrder);
        GoodsOrderArea goodsOrderArea = new GoodsOrderArea();
        BeanUtils.copyProperties(goodsOrder, goodsOrderArea);
        return goodsOrderArea;
    }
}
