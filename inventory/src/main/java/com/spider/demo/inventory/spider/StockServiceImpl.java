package com.spider.demo.inventory.spider;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.spider.demo.inventory.entity.Stock;
import com.spider.demo.inventory.sdk.data.*;
import com.spider.demo.inventory.sdk.interfaces.StockService;
import com.spider.demo.inventory.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Component("stock_manager")
public class StockServiceImpl implements StockService {

    @Resource
    private IStockService iStockService;

    /**
     * 锁库存
     * @param param
     * @return
     */
    @Override
    public LockStockRsp lockStock(LockStockParam param) {
        // 获取库存
        log.info("lock-stock-param {}", JSON.toJSONString(param));
        Stock stock = iStockService.lambdaQuery().eq(Stock :: getGoodCode,param.getGoodsCode()).one();
        if(Objects.isNull(stock)){
            return LockStockRsp.builder().lockStatus(false).remark("没有找到商品库存").build();
        }

        if(stock.getLockNumber().compareTo(BigDecimal.ZERO) > 0){
            return LockStockRsp.builder().lockStatus(false).remark("库存被锁住").build();
        }
        // 校验库存
        if(stock.getGoodNumber().subtract(param.getLockNumber()).compareTo(BigDecimal.ZERO) < 0){
            return LockStockRsp.builder().lockStatus(false).remark("库存不足").build();
        }
        // 设置库存
        stock.setLockNumber(stock.getLockNumber().add(param.getLockNumber()));
        iStockService.lambdaUpdate()
                .set(Stock::getLockNumber,stock.getLockNumber())
                .set(Stock :: getLockCode,param.getLockCode())
                .set(Stock :: getLockStatus,"LOCK")
                .eq(Stock::getGoodCode,param.getGoodsCode())
                .update();
        return LockStockRsp.builder().lockStatus(true).build();
    }

    /**
     * 扣减库存
     * @param param
     * @return
     */
    @Override
    public StockArea deductStock(DeductStockParam param) {
        log.info("deduct-stock-param {}", JSON.toJSONString(param));
        Stock stock = iStockService.lambdaQuery().eq(Stock :: getLockCode,param.getLockCode()).last("for update").one();
        Preconditions.checkArgument(Objects.nonNull(stock),"没有找到锁定的库存信息");
        // 扣减库存
        stock.setGoodNumber(stock.getGoodNumber().subtract(stock.getLockNumber()));
        iStockService.lambdaUpdate()
                .set(Stock::getGoodNumber,stock.getGoodNumber())
                .set(Stock :: getLockNumber,BigDecimal.ZERO)
                .set(Stock :: getLockStatus,"UNLOCK")
                .eq(Stock::getLockCode,param.getLockCode()).update();
        StockArea stockArea = new StockArea();
        BeanUtils.copyProperties(stock,stockArea);
        return stockArea;
    }

    /**
     * 释放库存
     * @param param
     * @return
     */
    @Override
    public StockArea releaseStock(ReleaseStockParam param) {
        log.info("release-stock-param {}", JSON.toJSONString(param));
        Stock stock = iStockService.lambdaQuery().eq(Stock :: getLockCode,param.getLockCode()).last("for update").one();
        Preconditions.checkArgument(Objects.nonNull(stock),"没有找到锁定的库存信息");
        // 释放锁住的库存
        stock.setLockNumber(BigDecimal.ZERO);
        iStockService.lambdaUpdate()
                .set(Stock::getLockNumber,stock.getLockNumber())
                .set(Stock :: getLockStatus,"UNLOCK")
                .eq(Stock::getLockCode,param.getLockCode())

                .update();
        StockArea stockArea = new StockArea();
        BeanUtils.copyProperties(stock,stockArea);
        return stockArea;
    }
}
