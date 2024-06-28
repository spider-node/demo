package com.spider.demo.order.service.impl;

import com.spider.demo.order.entity.GoodsOrder;
import com.spider.demo.order.mapper.GoodsOrderMapper;
import com.spider.demo.order.service.IGoodsOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author dds
 * @since 2024-06-14
 */
@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder> implements IGoodsOrderService {

}
