package com.spider.demo.pay.center.service.impl;

import com.spider.demo.pay.center.data.OtherPayParam;
import com.spider.demo.pay.center.entity.PayCenter;
import com.spider.demo.pay.center.mapper.PayCenterMapper;
import com.spider.demo.pay.center.service.IPayCenterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付表 服务实现类
 * </p>
 *
 * @author dds
 * @since 2024-06-14
 */
@Service
public class PayCenterServiceImpl extends ServiceImpl<PayCenterMapper, PayCenter> implements IPayCenterService {

    @Override
    public void pay(OtherPayParam param) {
        super.lambdaUpdate()
                .set(PayCenter :: getOrderStatus,param.getPayStatus())
                .eq(PayCenter::getPayOrder,param.getOrderNo())
                .update();
    }
}
