package com.spider.demo.inventory.service.impl;

import com.spider.demo.inventory.entity.Stock;
import com.spider.demo.inventory.mapper.StockMapper;
import com.spider.demo.inventory.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存表 服务实现类
 * </p>
 *
 * @author dds
 * @since 2024-06-14
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

}
