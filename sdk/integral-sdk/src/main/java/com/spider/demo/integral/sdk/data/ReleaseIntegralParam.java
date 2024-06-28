package com.spider.demo.integral.sdk.data;
import cn.spider.framework.annotation.StaTaskField;
import lombok.Data;
@Data
public class ReleaseIntegralParam {

    @StaTaskField("integral.lockCode")
    private String lockCode;
}
