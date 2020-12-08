package com.stewart.config.properties;

import com.stewart.config.factory.CustomPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

/**
 * @author stewart
 */
@Data
@Configuration
@PropertySource(value = "classpath:config.yml", factory = CustomPropertySourceFactory.class)
@ConfigurationProperties(prefix = "config.props")
public class ConfigYaml {

    private String str;

    private Integer num;

    private Boolean flag;

    private List<String> list;

    private Map<String, Object> map;

}
