# spring-boot-demo-base-hello

> demo-base-hello : spring-root 入门 HelloWorld

## 一、parent project

### 1.1 pom.xml：指定spring-boot依赖版本

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <spring.boot.version>2.3.6.RELEASE</spring.boot.version>
    </properties>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring.boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

## 二、current project

### 2.1 pom.xml

    <parent>
        <artifactId>spring-boot-demo</artifactId>
        <groupId>com.stewart</groupId>
        <version>1.0.0</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>demo-base-hello</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

### 2.2 spring boot 启动类

    @SpringBootApplication
    public class DemoBaseHello10001Application {

        public static void main(String[] args) {
            SpringApplication.run(DemoBaseHello10001Application.class, args);
        }

    }

启动类需添加@SpringBootApplication，且需要在项目中其它要被扫描注入spring容器代码层的外层包中。

### 2.3 控制器

    @RestController
    public class HelloController {
    
        @GetMapping("/hello")
        public String hello(String str) {
            return "hello " + str;
        }
    
    }

### 2.4 配置文件 application.yml

    server:
        port: 10001

## 三、测试验证

**运行** : ①直接运行启动类；②通过maven命令 mvn spring-boot:run

**访问 _localhost:10001/base/hello?name=Stewart_**