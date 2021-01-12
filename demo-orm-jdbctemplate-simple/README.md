# spring-boot-demo-orm-jdbctemplate

> demo-orm-jdbctemplate-simple : spring-boot 持久层 jdbctemplate简单使用

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
        <artifactId>spring-boot-starter-jdbc</artifactId>
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

### 1.3、数据操作层

dao层引入jdbcTemplate

    @Component
    public class DemoDao {
    
        @Autowired
        private JdbcTemplate jdbcTemplate;
    
        public int addDemo(Demo demo) {
            String insertSql = "insert into orm_demo (info,resume) value(?,?)";
            return jdbcTemplate.update(insertSql, demo.getInfo(), demo.getResume());
        }
    
        public int removeDemoById(Long id) {
            String removeSql = "delete from orm_demo where id = ?";
            return jdbcTemplate.update(removeSql, id);
        }
    
        public int updateDemoById(Demo demo) {
            String updateSql = "update orm_demo set info = ? , resume = ? where id = ? ";
            return jdbcTemplate.update(updateSql, demo.getInfo(), demo.getResume(), demo.getId());
        }
    
        public Demo queryDemoById(Long id) {
            String querySql = "select * from orm_demo where id = ?";
            return jdbcTemplate.queryForObject(querySql, new Object[]{id}, (rs, rowNum) -> {
                Demo demo = new Demo();
                demo.setId(rs.getLong("id"));
                demo.setInfo(rs.getString("info"));
                demo.setResume(rs.getString("resume"));
                demo.setDeleteFlag(rs.getInt("delete_flag"));
                demo.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
                demo.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
                return demo;
            });
        }
    
        public List<Demo> queryAllDemo() {
            String queryAllSql = "select * from orm_demo";
            return jdbcTemplate.query(queryAllSql, (rs, rowNum) -> {
                Demo demo = new Demo();
                demo.setId(rs.getLong("id"));
                demo.setInfo(rs.getString("info"));
                demo.setResume(rs.getString("resume"));
                demo.setDeleteFlag(rs.getInt("delete_flag"));
                demo.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
                demo.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
                return demo;
            });
        }
    
        public Integer getDemoNum(){
            String numSql = "select count(*) from orm_demo";
            return jdbcTemplate.queryForObject(numSql,Integer.class);
        }
    
    }

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
   
    @GetMapping("/queryAll")
    public List<Demo> queryAllDemo() {
        return demoService.queryAllDemo();
    }

    @GetMapping("/queryNum")
    public Integer getDemoNum() {
        return demoService.getDemoNum();
    }