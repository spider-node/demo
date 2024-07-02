package com.spider.demo.pay.center.spider;
import cn.spider.framework.common.config.Constant;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.spider.demo.pay.center.entity.PayCenter;
import com.spider.demo.pay.center.entity.enums.PayStatus;
import com.spider.demo.pay.center.sdk.data.*;
import com.spider.demo.pay.center.sdk.interfaces.PayService;
import com.spider.demo.pay.center.service.IPayCenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component("pay_manager")
public class PayServiceImpl implements PayService {

    @Resource
    private IPayCenterService payCenterService;

    /**
     * 创建支付中心订单
     * @param param 创建订单必要信息
     * @return 支付订单域实体
     */
    @Override
    public PayCenterArea createPayCenter(CreatePayOrderParam param) {
        log.info("create-pay-param {}", JSON.toJSONString(param));
        PayCenter payCenter = new PayCenter();
        payCenter.setPayMoney(payCenter.getPayMoney());
        payCenter.setPayOrder(UUID.randomUUID().toString());
        payCenter.setPayType(param.getPayType());
        payCenter.setPayUser(param.getPayUser());
        payCenter.setGoodCode(param.getGoodCode());
        payCenter.setOrderStatus(PayStatus.WAIT_PAID);
        payCenter.setOtherNo(param.getOtherNo());
        payCenterService.save(payCenter);
        PayCenterArea payCenterArea = new PayCenterArea();
        BeanUtils.copyProperties(payCenter, payCenterArea);
        return payCenterArea;
    }

    /**
     * 查询执行订单信息
     * @param param 三方订单号
     * @return 支付订单域实体
     */
    @Override
    public PayCenterArea selectPayCenter(SelectPayCenterParam param) {
        log.info("select-pay-center-param {}", JSON.toJSONString(param));
        PayCenter payCenter = payCenterService.lambdaQuery().eq(PayCenter::getOtherNo, param.getOtherNo()).one();
        log.info("select-pay-center-info {}", JSON.toJSONString(payCenter));
        PayCenterArea payCenterArea = new PayCenterArea();
        BeanUtils.copyProperties(payCenter, payCenterArea);
        payCenterArea.setOrderStatus(payCenter.getOrderStatus().name());
        return payCenterArea;
    }

    /**
     * 发起支付
     * @param param
     * @return 支付订单域实体
     */
    @Override
    public PayCenterArea pay(PayParam param) {
        PayCenter payCenter = payCenterService.lambdaQuery().eq(PayCenter::getOtherNo, param.getOtherNo()).one();
        log.warn("center-info-param {}", JSON.toJSONString(payCenter));
        if (payCenter != null) {
            payCenter.setOrderStatus(PayStatus.ING);
            payCenter.setPayType(param.getPayType());
            payCenterService.updateById(payCenter);
            log.warn("update-after-indfo{}", JSON.toJSONString(payCenter));
        }
        PayCenterArea payCenterArea = new PayCenterArea();
        BeanUtils.copyProperties(payCenter, payCenterArea);
        return payCenterArea;
    }

    /**
     * 更新支付状态
     * @param param
     */
    @Override
    public void updateStatus(UpdatePayOrderStatusParam param) {
        log.warn("update-pay-status-param {}", JSON.toJSONString(param));
        PayCenter payCenter = payCenterService.lambdaQuery().eq(PayCenter::getOtherNo, param.getOtherNo()).one();
        Preconditions.checkArgument(Objects.nonNull(payCenter), "支付单不存在");
        payCenter.setOrderStatus(PayStatus.valueOf(param.getOrderStatus()));
        log.warn("update-status-after-info {}", JSON.toJSONString(payCenter));
        payCenterService.updateById(payCenter);
    }

    /**
     * 查询订单是否进行支付
     *
     * @param param 第三方订单号
     * @return 返回订单状态
     */
    @Override
    public PayStatusRsp queryPayStatus(PayStatusParam param) {
        log.warn("queryPayStatus-param {}", JSON.toJSONString(param));
        PayCenter payCenter = payCenterService.lambdaQuery().eq(PayCenter::getOtherNo, param.getOtherNo()).one();
        String status = payCenter.getOrderStatus().equals(PayStatus.WAIT_PAID) ? Constant.WAIT : Constant.SUSS;
        log.warn("queryPayStatus-status {}", status);
        return PayStatusRsp.builder().status(status).build();
    }
}
