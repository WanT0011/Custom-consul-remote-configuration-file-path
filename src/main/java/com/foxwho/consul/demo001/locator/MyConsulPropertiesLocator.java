package com.foxwho.consul.demo001.locator;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.foxwho.consul.demo001.config.ConsulSourceProperties;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.cloud.consul.config.ConsulConfigProperties;
import org.springframework.cloud.consul.config.ConsulFilesPropertySource;
import org.springframework.cloud.consul.config.ConsulPropertySource;
import org.springframework.cloud.consul.config.ConsulPropertySourceLocator;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.cloud.consul.config.ConsulConfigProperties.Format.FILES;

/**
 * @author WangZhiJian
 * @since 2020/6/29
 */
@Order(0)
//@Import(ConsulSourceProperties.class)
public class MyConsulPropertiesLocator implements PropertySourceLocator {
    @Resource
    ConsulClient consul;
    @Resource
    private ConsulConfigProperties properties;
    @Resource
    private ConsulPropertySourceLocator consulPropertySourceLocator;
    @Resource
    ConsulSourceProperties consulSourceProperties;



    /**
     * @param environment the current Environment
     * @return a PropertySource or null if there is none
     * @throws IllegalStateException if there is a fail fast condition
     */
    @Override
    public PropertySource<?> locate(Environment environment) {
        //System.out.println("hello , i am source locator");
//        String propertySourceContext = "config/mydatasource/";

        /**
         * 这一段 copy的 consul 的Locate的代码，我们在初始化时需要进行从我们自动配置的路劲去加载配置
         */
        CompositePropertySource composite = new CompositePropertySource("myConsul");
        List<String> sources = consulSourceProperties.getSources();
        if(!CollectionUtils.isEmpty(sources)){
            for(String propertySourceContext : sources){
                try {
                    ConsulPropertySource propertySource = null;
                    if(this.properties.getFormat() == FILES){
                        Response<GetValue> response = this.consul.getKVValue(propertySourceContext, this.properties.getAclToken());
                        addIndex(propertySourceContext, response.getConsulIndex());
                        if (response.getValue() != null) {
                            ConsulFilesPropertySource filesPropertySource = new ConsulFilesPropertySource(propertySourceContext, this.consul, this.properties);
                            filesPropertySource.init(response.getValue());
                            propertySource = filesPropertySource;
                        }

                    }else{
                        propertySource = create(propertySourceContext);
                    }
                    if (propertySource != null) {
                        composite.addPropertySource(propertySource);
                    }
                } catch (Exception e) {
                    if (this.properties.isFailFast()) {
                        System.out.println("Fail fast is set and there was an error reading configuration from consul.");
                        ReflectionUtils.rethrowRuntimeException(e);
                    } else {
                        System.out.println("Unable to load consul config from "+ propertySourceContext+ e);
                    }
                }
            }
        }
        return composite;
    }

    private void addIndex(String propertySourceContext, Long consulIndex) {
        //insert my propertySourceContext
        //这一段，将我们加载的 路劲 的信息加入到 consul 官方的 ContextIndexes中，这样
        // 官方 的 watch就会自动监视我们的 路劲，实现配置更新
        consulPropertySourceLocator.getContextIndexes().put(propertySourceContext, consulIndex);
        System.out.println("consulPropertySourceLocator.getContextIndexes() = "+consulPropertySourceLocator.getContextIndexes());
    }

    private ConsulPropertySource create(String context) {
        ConsulPropertySource propertySource = new ConsulPropertySource(context, this.consul, this.properties);
        propertySource.init();
        addIndex(context, propertySource.getInitialIndex());
        return propertySource;
    }
}
