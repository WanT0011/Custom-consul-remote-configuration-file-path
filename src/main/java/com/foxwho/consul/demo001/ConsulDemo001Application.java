package com.foxwho.consul.demo001;

import com.foxwho.consul.demo001.config.MyConfigProperties;
import com.foxwho.consul.demo001.locator.MyConsulConfigBootstrapAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({ MyConfigProperties.class})//指定配置类
@Import(MyConsulConfigBootstrapAutoConfiguration.class)
public class ConsulDemo001Application {
    public static void main(String[] args) {
        SpringApplication.run(ConsulDemo001Application.class, args);
    }
}
