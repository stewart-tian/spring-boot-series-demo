# spring-boot-demo-orm-jdbctemplate-multi

> demo-orm-jdbctemplate-multi : jdbcTemplate + hikari 多数据源处理

## 一、配置

### 1.1、引入依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

### 1.2、参数配置

hikari没有url属性，多数据源配置时数据连接路径属性使用jdbc-url配置。

```yaml
server:
  port: 10112

spring:
  datasource:
    primary:
      driver-class-name: com.mysql.cj.jdbc.Driver
      jdbc-url: jdbc:mysql://127.0.0.1/boot_demo?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8
      username: boot
      password: boot1234
      type: com.zaxxer.hikari.HikariDataSource
      # Hikari 连接池配置
      hikari:
        # 最小空闲连接数量
        minimum-idle: 5
        # 空闲连接存活最大时间，默认600000（10分钟）
        idle-timeout: 180000
        # 连接池最大连接数，默认是10
        maximum-pool-size: 10
        # 连接池返回的连接的默认自动提交行为,默认值：true
        auto-commit: true
        # 连接池名称
        pool-name: PrimaryHikariCP
        # 连接池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟
        max-lifetime: 1800000
        # 数据库连接超时时间,默认30秒，即30000
        connection-timeout: 30000
        # 测试连接是否可用
        connection-test-query: SELECT 1
    second:
      driver-class-name: com.mysql.cj.jdbc.Driver
      jdbc-url: jdbc:mysql://127.0.0.1/boot_example?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8
      username: boot
      password: boot1234
      type: com.zaxxer.hikari.HikariDataSource
      # Hikari 连接池配置
      hikari:
        # 最小空闲连接数量
        minimum-idle: 5
        # 空闲连接存活最大时间，默认600000（10分钟）
        idle-timeout: 180000
        # 连接池最大连接数，默认是10
        maximum-pool-size: 10
        # 连接池返回的连接的默认自动提交行为,默认值：true
        auto-commit: true
        # 连接池名称
        pool-name: SecondHikariCP
        # 连接池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟
        max-lifetime: 1800000
        # 数据库连接超时时间,默认30秒，即30000
        connection-timeout: 30000
        # 测试连接是否可用
        connection-test-query: SELECT 1
```

### 1.3、数据源配置

```java

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate primaryJdbcTemplate() {
        return new JdbcTemplate(primaryDataSource());
    }

    @Bean
    public JdbcTemplate secondJdbcTemplate() {
        return new JdbcTemplate(secondDataSource());
    }

}
```

### 1.4、不同数据源使用

boot_demo库使用primaryJdbcTemplate，@Autowired使用byName注入

```java

@Component
public class DemoDao {

    @Autowired
    private JdbcTemplate@Component

    public class DemoDao {

        @Autowired
        private JdbcTemplate primaryJdbcTemplate;
        ;
    }
```

boot_example库使用secondJdbcTemplate

```java

@Component
public class ExampleDao {

    @Autowired
    private JdbcTemplate secondJdbcTemplate;
}
```

## 二、web

### 2.1、web验证

service中注入dao，controller中注入service，postman请求验证。
