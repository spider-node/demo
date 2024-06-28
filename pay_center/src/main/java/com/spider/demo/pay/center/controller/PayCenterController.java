package com.spider.demo.pay.center.controller;

import cn.spider.framework.linker.client.data.SpiderFunctionParam;
import com.spider.demo.pay.center.data.OtherPayParam;
import com.spider.demo.pay.center.entity.PayCenter;
import com.spider.demo.pay.center.service.IPayCenterService;
import io.vertx.core.json.JsonObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 支付表 前端控制器
 * </p>
 *
 * @author dds
 * @since 2024-06-14
 */
@RestController
@RequestMapping("/payCenter")
public class PayCenterController {

    @Resource
    private IPayCenterService iPayCenterService;

    /**
     * 设置支付成功与失败
     * @param param
     */
    @PostMapping("/pay")
    public void initiatePay(@RequestBody OtherPayParam param){
        iPayCenterService.lambdaUpdate().set(PayCenter :: getOrderStatus,param.getPayStatus()).eq(PayCenter :: getOtherNo,param.getOrderNo()).update();
    }
}
