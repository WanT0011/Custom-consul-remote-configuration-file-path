package com.foxwho.consul.demo001.locator;

import com.foxwho.consul.demo001.config.ConsulSourceProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.consul.ConditionalOnConsulEnabled;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bootstrap自动配置类
 *
 * @author WangZhiJian
 * @since 2020/6/29
 */
@Configuration
@ConditionalOnConsulEnabled
@ConditionalOnProperty(name = "spring.cloud.consul.config.enabled", matchIfMissing = true)
//@Import(ConsulSourceProperties.class)
public class MyConsulConfigBootstrapAutoConfiguration {

    /**
     * 引入 需要额外加载的consul的路劲
     * @return
     */
    @Bean
    public ConsulSourceProperties consulSourceProperties(){
        return new ConsulSourceProperties();
    }

    /**
     * 定义自己的consul Locator
     * @return
     */
    @Bean
    public MyConsulPropertiesLocator myLocator(){
        return new MyConsulPropertiesLocator();
    }

}
