# spring-boot-demo-orm-mybatis-pagehelper

> demo-orm-mybatis-pagehelper : spring-boot 持久层 mybatis 分页插件 pagehelper 简单使用

## 一、配置

### 1.1、引入依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.1.3</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper-spring-boot-starter</artifactId>
        <version>1.3.0</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

### 1.2、添加配置

pagehelper-spring-boot-starter 已经默认添加了配置，不添加配置已可使用

```yaml
pagehelper:
  # 分页插件会自动检测当前的数据库链接，自动选择合适的分页方式
  helper-dialect: mysql
  # 分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页。默认false 时，直接根据参数进行查询。
  reasonable: true
  # 支持通过 Mapper 接口参数来传递分页参数，默认值false，分页插件会从查询方法的参数值中，自动根据 params 配置的字段中取值，查找到合适的值时就会自动分页。
  support-methods-arguments: true
  # 为了支持startPage(Object params)方法，增加了该参数来配置参数映射，用于从对象中根据属性名取值， 可以配置 pageNum,pageSize,count,pageSizeZero,reasonable，不配置映射的用默认值， 默认值为pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero
  params: count=countSql
  # 默认值为 false，当该参数设置为 true 时，如果 pageSize=0 或者 RowBounds.limit = 0 就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是 Page 类型）
  page-size-zero: false
```

### 1.3、Mapper配置文件和Mapper接口

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.stewart.orm.mybatis.pagehelper.mapper.DemoMapper">

    <resultMap id="demoMap" type="com.stewart.orm.mybatis.pagehelper.entity.Demo">
        <id column="id" jdbcType="BIGINT" javaType="Long" property="id"/>
        <result column="info" jdbcType="VARCHAR" javaType="String" property="info"/>
        <result column="resume" jdbcType="VARCHAR" javaType="String" property="resume"/>
        <result column="delete_flag" jdbcType="TINYINT" javaType="Integer" property="deleteFlag"/>
        <result column="create_time" jdbcType="TIMESTAMP" javaType="java.time.LocalDateTime" property="createTime"/>
        <result column="update_time" jdbcType="TIMESTAMP" javaType="java.time.LocalDateTime" property="updateTime"/>
    </resultMap>

    <select id="queryAll" resultMap="demoMap">
        select * from orm_demo
    </select>

    <select id="queryPage" resultMap="demoMap">
        select * from orm_demo
    </select>

</mapper>
```

```java

@Mapper
public interface DemoMapper {

    /**
     * 查询所有
     *
     * @return 所有demo
     */
    List<Demo> queryAll();

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    Page<Demo> queryPage();

}
```

### 1.4、service实现

PageInfo会返回当前页数，页面条数，总页数，总条数等等信息；Page只会将数据分页返回，没有附带信息。

```java

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<Demo> queryAll() {
        return demoMapper.queryAll();
    }

    @Override
    public PageInfo<Demo> queryPage(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Demo> demos = demoMapper.queryAll();
        return new PageInfo<>(demos);
    }

    @Override
    public Page<Demo> queryPage2(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        return demoMapper.queryPage();
    }

}
```

