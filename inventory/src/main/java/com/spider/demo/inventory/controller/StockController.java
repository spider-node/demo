package com.spider.demo.inventory.controller;
import com.spider.demo.inventory.entity.Stock;
import com.spider.demo.inventory.service.IStockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 库存表 前端控制器
 * </p>
 *
 * @author dds
 * @since 2024-06-14
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Resource
    private IStockService stockService;

    // 新增库存
    @PostMapping("/create_stock")
    public void createBill(@RequestBody Stock param){
        stockService.save(param);
    }
    // 删除库存
    @PostMapping("/delete_stock")
    public void deleteStock(@RequestBody Stock param){
        stockService.removeById(param.getId());
    }
    // 修改库存
    @PostMapping("/update_stock")
    public void updateStock(@RequestBody Stock param) {
        stockService.lambdaUpdate().set(Stock :: getLockCode,param.getLockCode()).eq(Stock :: getId,param.getId()).update();
    }
}
