package com.foxwho.consul.demo001.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 *
 * @author want
 * @createTime 2020.07.18.16:36
 */
@ConfigurationProperties("want")
public class MyConfigProperties {

    private String name;

    private String flag;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
