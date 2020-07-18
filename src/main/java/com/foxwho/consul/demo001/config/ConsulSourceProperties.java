package com.foxwho.consul.demo001.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 需要去 consul 上加载的配置路劲的列表
 *
 * @author want
 * @createTime 2020.07.18.11:12
 */
@ConfigurationProperties(prefix = "com.want")
public class ConsulSourceProperties {
    private List<String> sources;

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }
}
