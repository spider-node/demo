package com.spider.demo.inventory.mapper;

import com.spider.demo.inventory.entity.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 库存表 Mapper 接口
 * </p>
 *
 * @author dds
 * @since 2024-06-14
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {

}
