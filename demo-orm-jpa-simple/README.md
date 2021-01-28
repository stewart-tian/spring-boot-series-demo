# spring-boot-demo-orm-jpa

> demo-orm-jpa-simple : spring-boot 持久层 jpa 简单使用

## 一、jpa配置

### 1.1 引入依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
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

```yaml
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
  jpa:
    properties:
      hibernate:
        hbm2ddl:
          # 启动项目新建数据表
          auto: create
        # 存储引擎
        dialect: org.hibernate.dialect.MySQL8Dialect
        # 格式化sql
        format_sql: true
    # 打印sql语句
    show-sql: true
```

**hibernate.hbm2ddl.auto 参数的作用主要用于：自动创建、更新、验证数据库表结构，有四个值。**

create：每次加载 Hibernate 时都会删除上一次生成的表，然后根据 model 类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。

create-drop：每次加载 Hibernate 时根据 model 类生成表，但是 sessionFactory 一关闭，表就自动删除。

update：最常用的属性，第一次加载 Hibernate 时根据 model 类会自动建立起表的结构（前提是先建立好数据库），以后加载 Hibernate 时根据 model
类自动更新表结构，即使表结构改变了，但表中的行仍然存在，不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。

validate ：每次加载 Hibernate 时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。

### 1.3、实体类

```java

@Data
@Entity
public class Example implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String info;

    private Boolean flag;

    private LocalDateTime createTime;

}
```

### 1.4、dao层

继承JpaRepository<Model,Key> ,即默认提供了一定的连接数据库的操作方法

```java
public interface ExampleRepository extends JpaRepository<Example, Long> {

}
```

### 1.4、serviceImpl

```java

@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    @Override
    public List<Example> findAll() {
        return exampleRepository.findAll();
    }

    @Override
    public Example addExample(Example example) {
        return exampleRepository.save(example);
    }

    @Override
    public Example findById(Long id) {
        Optional<Example> example = exampleRepository.findById(id);
        boolean present = example.isPresent();
        if (present) {
            return example.get();
        }
        return null;
    }


    @Override
    public Example updateExample(Example example) {
        return exampleRepository.save(example);
    }

    @Override
    public void deleteById(Long id) {
        exampleRepository.deleteById(id);
    }

}
```

### 1.5、控制层

```java

@RestController
@RequestMapping("/orm/jpa")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping("/example")
    public Example getExample(Long id) {
        return exampleService.findById(id);
    }

    @GetMapping("/examples")
    public List<Example> findAllExample() {
        return exampleService.findAll();
    }

    @PostMapping("/example")
    public Example addExample(@RequestBody Example example) {
        return exampleService.addExample(example);
    }

    @PutMapping("/example")
    public Example updateExample(@RequestBody Example example) {
        return exampleService.updateExample(example);
    }

    @DeleteMapping("/example")
    public void deleteExample(Long id) {
        exampleService.deleteById(id);
    }

}
```

启动项目、通过postman验证