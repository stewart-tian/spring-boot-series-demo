package com.stewart.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 通过指定前缀为配置参数赋值
 * 需为配置类添加 @Component 注解，spring-boot才会扫描加载该类
 *
 * @author stewart
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "stewart.project")
public class PrefixProperties {

    private String info;

    private Integer port;

}
