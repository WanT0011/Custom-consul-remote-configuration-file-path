package com.foxwho.consul.demo001.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author WangZhiJian
 * @since 2020/7/17
 */
@Component
public class MyRunner implements ApplicationRunner {

    @Value("${want.name:defaultName}")
    private String name;
    @Value("${want.flag:flag}")
    private String flag;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("name = " + name);
        System.out.println("flag = " + flag);
    }
}
