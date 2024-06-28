package com.spider.demo.order.config;

import cn.spider.framework.linker.client.config.CommonConfig;
import cn.spider.framework.linker.client.config.GrpcConfig;
import cn.spider.framework.linker.client.okhttp.SpiderClient;
import io.vertx.ext.web.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @BelongsProject: bms_middle_platform
 * @BelongsPackage: com.hope.saas.bms.middle.platform.config
 * @Author: dengdongsheng
 * @CreateTime: 2023-06-09  10:38
 * @Description: spider配置类
 * @Version: 1.0
 */
@Import({CommonConfig.class, GrpcConfig.class})
@Configuration
public class SpiderConfig {
    @Bean
    public SpiderClient buildSpiderClient(WebClient webClient, @Value("${spider.server.ip}") String spiderServerAddr, @Value("${spider.server.port}") String spiderServerPort){
        return new SpiderClient(webClient,spiderServerAddr,Integer.parseInt(spiderServerPort));
    }
}

