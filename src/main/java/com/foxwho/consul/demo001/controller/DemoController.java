package com.foxwho.consul.demo001.controller;

import com.foxwho.consul.demo001.config.MyConfigProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoController {


    /**
     * 使用 ConfigurationProperties 的则可以进行配置的修改，watch会生效
     *
     */
    @Resource
    private MyConfigProperties myConfigProperties;

    /**
     * 使用 {@code @Value} 注解的属性只有在启动时才能加载远程配置，后续的远程配置修改会无效化
     */
    @Value("${want.name:defaultName}")
    private String name;
    @Value("${want.flag:false}")
    private String flag;



    @RequestMapping("/getName")
    public String getName(){
        return myConfigProperties.getName();
    }

    @RequestMapping("/getFlag")
    public String getFlag(){
        return myConfigProperties.getFlag();
    }

    @RequestMapping("/getValueName")
    public String getValueName(){
        return name;
    }

    @RequestMapping("/getValueFlag")
    public String getValueFlag(){
        return flag;
    }
}
