package com.spider.demo.integral.spider;

import com.alibaba.fastjson.JSON;
import com.spider.demo.integral.entity.Integral;
import com.spider.demo.integral.sdk.data.*;
import com.spider.demo.integral.sdk.interfaces.IntegralService;
import com.spider.demo.integral.service.IIntegralService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component("integral_manager")
public class IntegralServiceImpl implements IntegralService {

    @Resource
    private IIntegralService iIntegralService;

    /**
     * 计算计费抵扣
     * @param param 用户与兑换金额
     * @return
     */
    @Override
    public CalculateDeductionResult calculateDeduction(CalculateDeductionParam param) {
        log.info("calculate-deduction-param {}", JSON.toJSONString(param));
        // 获取积分
        Integral integral = iIntegralService.lambdaQuery().eq(Integral::getUser,param.getUser()).last("limit 1").one();
        if(Objects.isNull(integral)){
            integral = Integral.builder()
                    .integralNum(BigDecimal.ZERO)
                    .integralCode(UUID.randomUUID().toString())
                    .user(param.getUser())
                    .lockNumber(BigDecimal.ZERO)
                    .lockStatus("UNLOCK")
                    .build();
        }
        BigDecimal sumIntegral = integral.getIntegralNum().subtract(integral.getLockNumber());
        // 计算积分
        BigDecimal deductionIntegral = sumIntegral.divide(new BigDecimal("10"),2);
        log.info("calculate-deduction-integral {}", deductionIntegral);

        iIntegralService.saveOrUpdate(integral);
        // 返回计算信息
        return CalculateDeductionResult.builder()
                .deductionAmount(deductionIntegral)
                .deductionAfterAmount(param.getAmount().subtract(deductionIntegral))
                .userIntegral(deductionIntegral.multiply(new BigDecimal("10")))
                .remainderIntegral(sumIntegral.subtract(deductionIntegral.multiply(new BigDecimal("10"))))
                .build();
    }

    /**
     * 锁积分
     * @param param 用户与积分数量
     * @return
     */
    @Override
    public IntegralArea lockIntegral(LockIntegralParam param) {
        log.info("calculate-deduction-param {}", JSON.toJSONString(param));
        Integral integral = iIntegralService.lambdaQuery().eq(Integral::getUser,param.getUser()).last("limit 1").one();
        integral.setLockCode(param.getLockCode());
        integral.setLockNumber(param.getIntegral());
        // 对积分进行锁定
        integral.setLockStatus("LOCK");
        iIntegralService.updateById(integral);
        IntegralArea integralArea = IntegralArea.builder().build();
        BeanUtils.copyProperties(integral,integralArea);
        return integralArea;
    }

    /**
     * 扣除积分
     * @param param
     * @return
     */
    @Override
    public IntegralArea deductionIntegral(DeductionIntegralParam param) {
        log.info("deduction-integral-param {}", JSON.toJSONString(param));
        Integral integral = iIntegralService.lambdaQuery().eq(Integral::getLockCode,param.getLockCode()).last("limit 1").one();
        // 扣除积分
        integral.setIntegralNum(integral.getIntegralNum().subtract(integral.getLockNumber()));
        integral.setLockNumber(BigDecimal.ZERO);
        integral.setLockStatus("LOCK");
        integral.setLockCode("");
        log.info("deduction-integral-after {}", JSON.toJSONString(integral));
        iIntegralService.updateById(integral);
        IntegralArea integralArea = IntegralArea.builder().build();
        BeanUtils.copyProperties(integral,integralArea);
        return integralArea;
    }

    /**
     * 释放积分
     * @param param
     * @return
     */
    @Override
    public IntegralArea releaseIntegral(ReleaseIntegralParam param) {
        log.info("release-integral-param {}", JSON.toJSONString(param));

        Integral integral = iIntegralService.lambdaQuery().eq(Integral::getLockCode,param.getLockCode()).last("limit 1").one();
        integral.setLockNumber(BigDecimal.ZERO);
        integral.setLockStatus("UNLOCK");
        integral.setLockCode("");
        log.info("release-integral-after {}", JSON.toJSONString(integral));
        iIntegralService.updateById(integral);
        IntegralArea integralArea = IntegralArea.builder().build();
        BeanUtils.copyProperties(integral,integralArea);
        return integralArea;
    }

    /**
     * 增加积分
     * @param param
     * @return
     */
    @Override
    public IntegralArea addIntegral(AddIntegralParam param) {
        log.info("add-integral-param {}", JSON.toJSONString(param));
        Integral integral = iIntegralService.lambdaQuery().eq(Integral::getUser,param.getUser()).last("limit 1").one();
        integral.setIntegralNum(integral.getIntegralNum().add(param.getIntegralNum()));
        iIntegralService.updateById(integral);
        log.info("add-integral-info {}", JSON.toJSONString(integral));
        IntegralArea integralArea = IntegralArea.builder().build();
        BeanUtils.copyProperties(integral,integralArea);
        return integralArea;
    }
}
