# spring-boot-demo-orm-mybatis

> demo-orm-mybatis-simple : spring-boot 持久层 mybatis简单使用

## 一、基础配置

### 1.1、引入依赖

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

### 1.2、数据源配置

在全局配置文件中添加数据源配置，使用hikari连接池，spring-boot内置hikari连接池，不用引入jar包，直接配置即可使用

    spring:
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://127.0.0.1/boot_demo?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8
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
          pool-name: HikariCP
          # 连接池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟
          max-lifetime: 1800000
          # 数据库连接超时时间,默认30秒，即30000
          connection-timeout: 30000
          # 测试连接是否可用
          connection-test-query: SELECT 1
    mybatis:
      # 指定mapper文件匹配路径
      mapper-locations: classpath:mappers/*Mapper.xml
      configuration:
        # sql日志打印
        log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
        # 下划线转驼峰
        map-underscore-to-camel-case: true

### 1.3、定义Mapper接口和配置mapper配置文件

Mapper接口

    package com.stewart.orm.mybatis.mapper;
    
    import com.stewart.orm.mybatis.entity.Demo;
    import org.apache.ibatis.annotations.Mapper;
    
    @Mapper
    public interface DemoMapper {
    
      int addDemo(Demo demo);

      int removeDemoById(Long id);

      int updateDemoById(Demo demo);

      Demo queryDemoById(Long id);
    
    }

mapper配置文件

    <mapper namespace="com.stewart.orm.mybatis.mapper.DemoMapper">

        <resultMap id="demoMap" type="com.stewart.orm.mybatis.entity.Demo">
            <id column="id" jdbcType="BIGINT" javaType="Long" property="id"/>
            <result column="info" jdbcType="VARCHAR" javaType="String" property="info"/>
            <result column="resume" jdbcType="VARCHAR" javaType="String" property="resume"/>
            <result column="delete_flag" jdbcType="TINYINT" javaType="Integer" property="deleteFlag"/>
            <result column="create_time" jdbcType="TIMESTAMP" javaType="java.time.LocalDateTime" property="createTime"/>
            <result column="update_time" jdbcType="TIMESTAMP" javaType="java.time.LocalDateTime" property="updateTime"/>
        </resultMap>

        <insert id="addDemo" parameterType="com.stewart.orm.mybatis.entity.Demo">
            insert into orm_demo (info,resume) value(#{info},#{resume})
        </insert>
    
        <delete id="removeDemoById" parameterType="Long">
            delete from orm_demo where id = #{id}
        </delete>
    
        <update id="updateDemoById" parameterType="com.stewart.orm.mybatis.entity.Demo">
            update orm_demo set info = #{info},resume = #{resume} where id = #{id}
        </update>
    
        <select id="queryDemoById" resultMap="demoMap" parameterType="Long">
            select * from orm_demo where id = #{id}
        </select>
    
    </mapper>

配置文件中mapper标签的namespace属性值对应于Mapper接口全类名，其子标签的用于执行的标签的id与Mapper接口方法名需一致。

### 1.4验证

service引入mapper，controller引入service

    @Autowired
    private DemoService demoService;

    @PostMapping("/addDemo")
    public int addDemo(@RequestBody Demo demo) {
        return demoService.addDemo(demo);
    }

    @DeleteMapping("/removeDemo")
    public int removeDemoById(Long id) {
        return demoService.removeDemoById(id);
    }

    @PutMapping("/updateDemo")
    public int updateDemoById(@RequestBody Demo demo) {
        return demoService.updateDemoById(demo);
    }

    @GetMapping("/queryDemo")
    public Demo queryDemoById(Long id) {
        return demoService.queryDemoById(id);
    }