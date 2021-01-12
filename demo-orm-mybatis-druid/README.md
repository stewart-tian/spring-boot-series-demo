# spring-boot-demo-orm-mybatis-druid

> demo-orm-mybatis-druid : spring-boot 持久层 mybatis+druid 简单使用

## 一、基础配置

### 1.1、引入依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>${mybatis.version}</version>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.1.22</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

### 1.2 添加配置

```yaml
server:
  port: 10102

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1/boot_demo?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8
    username: boot
    password: boot1234
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      # 配置初始化大小，最小，最大
      initial-size: 10
      min-idle: 10
      max-active: 50
      # 配置获取连接等待超时的时间
      max-wait: 60000
      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位毫秒
      time-between-eviction-runs-millis: 60000
      # 配置一个连接在池中最小生存时间(毫秒)
      min-evictable-idle-time-millis: 300000
      # 用来检测连接是否有效的sql
      validation-query: SELECT 1
      # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
      test-while-idle: true
      # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
      test-on-borrow: false
      # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
      test-on-return: false
      # 打开 PSCache，并且指定每个连接上 PSCache 的大小。是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
      pool-prepared-statements: false
      max-pool-prepared-statement-per-connection-size: 100
      # 配置监控统计拦截的 Filter，去掉后监控界面 SQL 无法统计，wall 用于防火墙
      filters: stat,wall
      # 通过 connection-properties 属性打开 mergeSql 功能；慢 SQL 记录
      connection-properties: druid.stat.mergeSql\=true;druid.stat.slowSqlMillis\=5000
      # 配置 DruidStatFilter
      web-stat-filter:
        enabled: true
        url-pattern: /*
        exclusions: .js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*
      # 配置 DruidStatViewServlet
      stat-view-servlet:
        # 开启
        enabled: true
        url-pattern: /druid/*
        # 禁用 HTML 中 Reset All 按钮
        reset-enable: false
        # 登录用户名/密码
        login-username: stewart
        login-password: 123456

mybatis:
  # 指定mapper文件匹配路径
  mapper-locations: classpath:mappers/*Mapper.xml
  configuration:
    # sql日志打印
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    # 下划线转驼峰
    map-underscore-to-camel-case: true
```

### 1.3、定义Mapper接口和配置mapper配置文件

Mapper接口

```java
package com.stewart.orm.mybatis.druid.mapper;

import com.stewart.orm.mybatis.druid.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoMapper {

    /**
     * 增
     *
     * @param demo demo
     * @return 增数
     */
    int save(Demo demo);

    /**
     * 删
     *
     * @param id 主键
     * @return 删数
     */
    int removeById(Long id);

    /**
     * 改
     *
     * @param demo demo
     * @return 改数
     */
    int updateById(Demo demo);

    /**
     * 查
     *
     * @param id 主键
     * @return demo
     */
    Demo queryById(Long id);

    /**
     * 查询所有
     *
     * @return 所有demo
     */
    List<Demo> queryAll();

}
```

mapper配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.stewart.orm.mybatis.druid.mapper.DemoMapper">

    <resultMap id="demoMap" type="com.stewart.orm.mybatis.druid.entity.Demo">
        <id column="id" jdbcType="BIGINT" javaType="Long" property="id"/>
        <result column="info" jdbcType="VARCHAR" javaType="String" property="info"/>
        <result column="resume" jdbcType="VARCHAR" javaType="String" property="resume"/>
        <result column="delete_flag" jdbcType="TINYINT" javaType="Integer" property="deleteFlag"/>
        <result column="create_time" jdbcType="TIMESTAMP" javaType="java.time.LocalDateTime" property="createTime"/>
        <result column="update_time" jdbcType="TIMESTAMP" javaType="java.time.LocalDateTime" property="updateTime"/>
    </resultMap>


    <insert id="save" parameterType="com.stewart.orm.mybatis.druid.entity.Demo">
        insert into orm_demo (info,resume) value(#{info},#{resume})
    </insert>

    <delete id="removeById" parameterType="Long">
        delete from orm_demo where id = #{id}
    </delete>

    <update id="updateById" parameterType="com.stewart.orm.mybatis.druid.entity.Demo">
        update orm_demo set info = #{info},resume = #{resume} where id = #{id}
    </update>

    <select id="queryById" resultMap="demoMap" parameterType="Long">
        select * from orm_demo where id = #{id}
    </select>

    <select id="queryAll" resultMap="demoMap">
        select * from orm_demo;
    </select>

</mapper>
```

### 1.4、引用

Service实现引用Mapper接口，Controller引用Service

### 1.5、druid

监控，访问：http://localhost:10102/druid