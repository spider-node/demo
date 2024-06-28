package com.spider.demo.pay.center.service;

import com.spider.demo.pay.center.data.OtherPayParam;
import com.spider.demo.pay.center.entity.PayCenter;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 支付表 服务类
 * </p>
 *
 * @author dds
 * @since 2024-06-14
 */
public interface IPayCenterService extends IService<PayCenter> {
    void pay(OtherPayParam param);
}
