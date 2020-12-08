package com.stewart.web;

import com.stewart.config.properties.ConfigProperties;
import com.stewart.config.properties.ConfigYaml;
import com.stewart.config.properties.PrefixProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stewart
 */
@RestController
@RequestMapping("/base")
public class PropertiesController {

    @Value("${project.name}")
    private String projectName;

    @Value("${stewart.project.info}")
    private String envProjectName;

    @Autowired
    private PrefixProperties prefixProperties;

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ConfigYaml configYaml;

    /**
     * 1、直接引入application.yml全局配置文件中配置参数
     */
    @GetMapping("/prop")
    public String projectProperties() {
        System.out.println(projectName);
        return projectName;
    }

    /**
     * 2、通过前缀引入到配置对象中
     */
    @GetMapping("/prop/prefix")
    public String importPropertiesByPrefix() {
        System.out.println(prefixProperties);
        return prefixProperties.toString();
    }

    /**
     * 3、引入指定资源配置文件
     */
    @GetMapping("/prop/file")
    public String otherFileProperties() {
        System.out.println(configProperties);
        return configProperties.toString();
    }

    /**
     * 4、引入yaml配置
     */
    @GetMapping("/prop/yaml")
    public String yamlFileProperties() {
        System.out.println(configYaml);
        return configYaml.toString();
    }

    /**
     * 5、不同环境配置参数
     */
    @GetMapping("/prop/env")
    public String envProperties() {
        System.out.println(envProjectName);
        return envProjectName;
    }

}
