package com.spider.demo.order.spider;

import com.alibaba.fastjson.JSON;
import com.spider.demo.order.entity.GoodsOrder;
import com.spider.demo.order.sdk.data.CreateGoodsOrderParam;
import com.spider.demo.order.sdk.data.GoodsOrderArea;
import com.spider.demo.order.sdk.data.UpdateDeductionParam;
import com.spider.demo.order.sdk.data.UpdateOrderInfoParam;
import com.spider.demo.order.sdk.interfaces.GoodsOrderService;
import com.spider.demo.order.service.IGoodsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@Component("goods_order_manager")
public class GoodsOrderServiceImpl implements GoodsOrderService {

    @Resource
    private IGoodsOrderService iGoodsOrderService;

    /**
     * 创建订单
     * @param param
     * @return
     */
    @Override
    public GoodsOrderArea createOrder(CreateGoodsOrderParam param) {
        log.info("create-order-param {}", JSON.toJSONString(param));
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

    /**
     * 更新订单信息
     * @param param
     * @return
     */
    @Override
    public GoodsOrderArea updateOrderInfo(UpdateOrderInfoParam param) {
        log.info("update-order-info-param {}", JSON.toJSONString(param));
        iGoodsOrderService.lambdaUpdate()
                .set(GoodsOrder :: getOrderStatus,param.getOrderStatus())
                .set(StringUtils.isNotEmpty(param.getRemarks()),GoodsOrder :: getRemarks,param.getRemarks())
                .eq(GoodsOrder :: getOrderNo,param.getOrderNo()).update();
        GoodsOrder goodsOrder = iGoodsOrderService.lambdaQuery().eq(GoodsOrder::getOrderNo,param.getOrderNo()).one();
        GoodsOrderArea goodsOrderArea = new GoodsOrderArea();
        BeanUtils.copyProperties(goodsOrder, goodsOrderArea);
        return goodsOrderArea;
    }

    /**
     * 更新订单抵扣金额
     * @param param
     * @return
     */
    @Override
    public GoodsOrderArea updateDeduction(UpdateDeductionParam param) {
        log.info("update-deduction-param {}", JSON.toJSONString(param));
        GoodsOrder goodsOrder = iGoodsOrderService.lambdaQuery().eq(GoodsOrder :: getOrderNo,param.getOrderNo()).one();
        goodsOrder.setDeductionAmount(param.getDeductionAmount());
        goodsOrder.setActualPayment(goodsOrder.getOrderMoney().subtract(param.getDeductionAmount()));
        iGoodsOrderService.updateById(goodsOrder);
        log.info("update-deduction-after-info {}", JSON.toJSONString(goodsOrder));
        GoodsOrderArea goodsOrderArea = new GoodsOrderArea();
        BeanUtils.copyProperties(goodsOrder, goodsOrderArea);
        return goodsOrderArea;
    }
}
