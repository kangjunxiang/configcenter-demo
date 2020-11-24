package com.example.demo.admin.controller;

import com.example.demo.common.core.domain.AjaxResult;
import org.antframework.configcenter.client.Config;
import org.antframework.configcenter.spring.ConfigsContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class DemoController {

    // 配置变更后，自动刷新redisHost字段
    @Value("${redis.host}")
    private String redisHost;
    // 配置变更后，自动刷新redisHost字段
    @Value("${redis.host}")
    private String redisHost2;

    private String redisHost3;

    // 配置变更后，自动重新调用setPort方法
    @Value("${redis.host}")
    public void setPort(String host){
        redisHost3 = host;
    }
    /**
     * 获取动态参数
     */
    @GetMapping("/getDynamic")
    public AjaxResult getDynamic(HttpServletResponse response) throws IOException {
        System.out.println(redisHost);
        Config config = ConfigsContexts.getConfig("customer");
        String redisHostFromConfig = config.getProperties().getProperty("redis.host");
        System.out.println(redisHostFromConfig);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("redisHost",redisHost);
        ajax.put("redisHostFromConfig",redisHostFromConfig);
        ajax.put("redisHost2",redisHost2);
        ajax.put("redisHost3",redisHost3);
        return ajax;
    }

}
