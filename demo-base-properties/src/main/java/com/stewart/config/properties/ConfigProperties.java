package com.stewart.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

/**
 * 注解@PropertySource默认不支持yml
 *
 * @author stewart
 */
@Data
@Configuration
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix = "config.params")
public class ConfigProperties {

    private String str;

    private Integer num;

    private Boolean flag;

    private List<String> list;

    private Map<String, Object> map;

}
