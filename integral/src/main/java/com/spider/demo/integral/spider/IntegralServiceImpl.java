package com.spider.demo.integral.spider;

import com.spider.demo.integral.entity.Integral;
import com.spider.demo.integral.sdk.data.*;
import com.spider.demo.integral.sdk.interfaces.IntegralService;
import com.spider.demo.integral.service.IIntegralService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Component("integral_manager")
public class IntegralServiceImpl implements IntegralService {

    @Resource
    private IIntegralService iIntegralService;


    @Override
    public CalculateDeductionResult calculateDeduction(CalculateDeductionParam param) {
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
        BigDecimal deductionIntegral = sumIntegral.divide(new BigDecimal("10"),2);
        iIntegralService.saveOrUpdate(integral);
        return CalculateDeductionResult.builder()
                .deductionAmount(deductionIntegral)
                .deductionAfterAmount(param.getAmount().subtract(deductionIntegral))
                .userIntegral(deductionIntegral.multiply(new BigDecimal("10")))
                .remainderIntegral(sumIntegral.subtract(deductionIntegral.multiply(new BigDecimal("10"))))
                .build();
    }

    @Override
    public IntegralArea lockIntegral(LockIntegralParam param) {
        Integral integral = iIntegralService.lambdaQuery().eq(Integral::getUser,param.getUser()).last("limit 1").one();
        integral.setLockCode(param.getLockCode());
        integral.setLockNumber(param.getIntegral());
        integral.setLockStatus("LOCK");
        iIntegralService.updateById(integral);
        IntegralArea integralArea = IntegralArea.builder().build();
        BeanUtils.copyProperties(integral,integralArea);
        return integralArea;
    }

    @Override
    public IntegralArea deductionIntegral(DeductionIntegralParam param) {
        Integral integral = iIntegralService.lambdaQuery().eq(Integral::getLockCode,param.getLockCode()).last("limit 1").one();
        integral.setIntegralNum(integral.getIntegralNum().subtract(integral.getLockNumber()));
        integral.setLockNumber(BigDecimal.ZERO);
        integral.setLockStatus("LOCK");
        integral.setLockCode("");
        iIntegralService.updateById(integral);
        IntegralArea integralArea = IntegralArea.builder().build();
        BeanUtils.copyProperties(integral,integralArea);
        return integralArea;
    }

    @Override
    public IntegralArea releaseIntegral(ReleaseIntegralParam param) {
        Integral integral = iIntegralService.lambdaQuery().eq(Integral::getLockCode,param.getLockCode()).last("limit 1").one();
        integral.setLockNumber(BigDecimal.ZERO);
        integral.setLockStatus("UNLOCK");
        integral.setLockCode("");
        iIntegralService.updateById(integral);
        IntegralArea integralArea = IntegralArea.builder().build();
        BeanUtils.copyProperties(integral,integralArea);
        return integralArea;
    }

    @Override
    public IntegralArea addIntegral(AddIntegralParam param) {
        Integral integral = iIntegralService.lambdaQuery().eq(Integral::getUser,param.getUser()).last("limit 1").one();
        integral.setIntegralNum(integral.getIntegralNum().add(param.getIntegralNum()));
        iIntegralService.updateById(integral);
        IntegralArea integralArea = IntegralArea.builder().build();
        BeanUtils.copyProperties(integral,integralArea);
        return integralArea;
    }
}
