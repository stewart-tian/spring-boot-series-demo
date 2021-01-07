# spring-boot-demo-base-actuator

> demo-base-actuator : spring boot 应用监控

1.1、引入依赖

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>

spring-boot项目引入spring-boot-starter-actuator依赖，默认即开放了info和简单的health端点 默认访问根路径：/actuator

1.2 添加配置

```yaml
management:
  server:
    # 设置监控端口号
    port: 10000
  endpoints:
    web:
      exposure:
        # 开放所有端点
        include: "*"
      # 开放的端点访问的根路径
      base-path: /actuator/demo
  endpoint:
    # 开放停用服务端点，post请求
    shutdown:
      enabled: true
    # 显示详细的health信息
    health:
      show-details: ALWAYS
```

1.3 验证

访问 
http://localhost:10003/actuator/demo    查看开放的端点
http://localhost:10000/actuator/demo/health     查看应用健康状态
post 请求 http://localhost:10000/actuator/demo/shutdown 停用应用