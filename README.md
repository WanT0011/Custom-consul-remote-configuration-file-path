#Spring boot 2.x Custom-consult-remote-configuration-file-path

实现功能：
    自定义项目去consul拉取配置的路劲，在配置文件中加入一下形式即可
    
    com:
      want:
        sources:
          application/config/
          application/want/
          
config下的是一些配置属性的包装类

DemoController提供web查看属性

Locator下提供的MyConsulPropertiesLocator为主要的提取类，会遍历配置的路劲取获取consul的配置。

MyConsulConfigBootstrapAutoConfiguration为spring cloud的自动配置类，启动级别高

runner下的是一个简单的控制台输出