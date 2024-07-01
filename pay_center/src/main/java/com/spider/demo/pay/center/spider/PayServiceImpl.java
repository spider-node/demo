package com.spider.demo.pay.center.spider;
import cn.spider.framework.common.config.Constant;
import com.google.common.base.Preconditions;
import com.spider.demo.pay.center.entity.PayCenter;
import com.spider.demo.pay.center.entity.enums.PayStatus;
import com.spider.demo.pay.center.sdk.data.*;
import com.spider.demo.pay.center.sdk.interfaces.PayService;
import com.spider.demo.pay.center.service.IPayCenterService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;

@Component("pay_manager")
public class PayServiceImpl implements PayService {

    @Resource
    private IPayCenterService payCenterService;

    /**
     * 创建支付中心订单
     * @param param
     * @return
     */
    @Override
    public PayCenterArea createPayCenter(CreatePayOrderParam param) {
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
     * @param param
     * @return
     */
    @Override
    public PayCenterArea selectPayCenter(SelectPayCenterParam param) {
        PayCenter payCenter = payCenterService.lambdaQuery().eq(PayCenter::getOtherNo, param.getOtherNo()).one();
        PayCenterArea payCenterArea = new PayCenterArea();
        BeanUtils.copyProperties(payCenter, payCenterArea);
        payCenterArea.setOrderStatus(payCenter.getOrderStatus().name());
        return payCenterArea;
    }

    /**
     * 发起支付
     * @param param
     * @return
     */
    @Override
    public PayCenterArea pay(PayParam param) {
        PayCenter payCenter = payCenterService.lambdaQuery().eq(PayCenter::getOtherNo, param.getOtherNo()).one();
        if (payCenter != null) {
            payCenter.setOrderStatus(PayStatus.ING);
            payCenter.setPayType(param.getPayType());
            payCenterService.updateById(payCenter);
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
        PayCenter payCenter = payCenterService.lambdaQuery().eq(PayCenter::getOtherNo, param.getOtherNo()).one();
        Preconditions.checkArgument(Objects.nonNull(payCenter), "支付单不存在");
        payCenter.setOrderStatus(PayStatus.valueOf(param.getOrderStatus()));
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
        PayCenter payCenter = payCenterService.lambdaQuery().eq(PayCenter::getOtherNo, param.getOtherNo()).one();
        String status = payCenter.getOrderStatus().equals(PayStatus.WAIT_PAID) ? Constant.WAIT : Constant.SUSS;
        return PayStatusRsp.builder().status(status).build();
    }
}
