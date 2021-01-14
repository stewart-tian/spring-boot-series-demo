package com.stewart.base.swagger2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author stewart
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为指定报下Controller生成API
                .apis(RequestHandlerSelectors.basePackage("com.stewart.base.swagger2.web"))
                // 为有@Api注解的Controller生成API
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // 为有@ApiOperation注解的方法生成API
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 为任何接口生成API文档
                //.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        // 维护人信息
        Contact contact = new Contact("Stewart", "https://github.com/stewart-tian/spring-boot-series-demo", "stewarttian@aliyun.com");
        return new ApiInfoBuilder()
                .title("Spring-boot-swagger2-demo")
                .description("Spring-boot-swagger2-demo")
                .contact(contact)
                .version("1.0.0")
                .build();
    }

}
