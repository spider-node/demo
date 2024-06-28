package com.spider.demo.order.controller;

import cn.spider.framework.linker.client.data.SpiderFunctionParam;
import cn.spider.framework.linker.client.okhttp.SpiderClient;
import com.spider.demo.order.entity.PlaceOrderParam;
import io.vertx.core.json.JsonObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 * @author dds
 * @since 2024-06-14
 */
@RestController
@RequestMapping("/order")
public class GoodsOrderController {

    @Resource
    private SpiderClient spiderClient;

    @PostMapping("/create_order")
    public void createBill(@RequestBody PlaceOrderParam param){
        SpiderFunctionParam spiderParam = new SpiderFunctionParam();
        spiderParam.setFunctionId("05fc0340-6a19-4141-a553-93154d3621e6");
        spiderParam.setRequest(param);
        spiderClient.startFunctionOkhttp(JsonObject.mapFrom(spiderParam));
    }

}
