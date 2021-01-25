# spring-boot-demo-base-swagger2

> demo-base-swagger2 : spring-boot 使用 swagger2

## 一、配置

### 1.1、引入依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>${swagger.version}</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>${swagger.version}</version>
    </dependency>
</dependencies>
```

## 1.2、swagger配置

@EnableSwagger2注解可以放在启动类上，不做相关配置即可使用，默认扫描全局

```java

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为指定包下Controller生成API
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
```

### 1.3、swagger使用

控制器类上添加@Api注解，tags属性：说明类的作用，swagger包扫描时，@Api注解仅用于说明

```java

@RestController
@RequestMapping("/base/swagger")
@Api(tags = "Swagger2功能介绍控制器")
public class SwaggerIntroduceController {

}
```

控制器上的方法,@ApiOperation用于方法上

    @GetMapping("/hello")
    @ApiOperation(value = "方法简述", notes = "方法详细说明")
    public String hello(){
        return"Hello";
    }

方法上加@ApiImplicitParam，name属性指定参数，paramType属性指定参数请求方式，example属性为参数添加值，required属性指明参数是否必须

paramType属性用值：path, query, body, header, form

    /**
     * 此处使用@ApiImplicitParam(name = "param", value = "入参", paramType = "query", example = "Param输入参数",required = true)也可以
     *
     * @param param 入参
     * @return 返回入参
     */
    @GetMapping("/one")
    @ApiOperation(value = "单个String入参", notes = "单String入参，单String反参")
    @ApiImplicitParams(@ApiImplicitParam(name = "param", value = "入参", paramType = "query", example = "Params输入参数",required = true))
    public String oneParam(@RequestParam("param") String param) {
        return "input param : " + param;
    }

    @GetMapping("/path/{var}")
    @ApiOperation(value = "path入参", notes = "path入参，单String反参")
    @ApiImplicitParam(name = "var", value = "入参", paramType = "path", example = "path输入参数",required = true)
    public String pathParam(@RequestParam("var") String var) {
        return "input param : " + var;
    }

    @GetMapping("/two")
    @ApiOperation(value = "多个String入参", notes = "多String入参，单String反参")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paramOne", value = "入参一", paramType = "query", example = "Params输入参数一",required = true),
            @ApiImplicitParam(name = "paramTwo", value = "入参二", paramType = "query", example = "Params输入参数二",required = true)
    })
    public String twoParam(@RequestParam("paramOne") String paramOne, @RequestParam("paramTwo") String paramTwo) {
        return "input params : " + paramOne + "+" + paramTwo;
    }

入参添加@RequestBody,Swagger页面将参数格式化为json格式，并赋予默认值。控制器内@RequestMapping注解的方法入参对象，会在swagger页面models生成对象说明。

通过给入参对象类添加@ApiModel和属性添加@ApiModelProperty可以给Swagger页面中该Models添加说明，更改默认值

    @PostMapping("/obj")
    @ApiOperation(value = "@RequestBody格式入参", notes = "json入参，String反参")
    public String objParam(@RequestBody IntroduceReq req) {
        return req.toString();
    }

    @PostMapping("/objRet")
    @ApiOperation(value = "@RequestBody格式入参，json反参", notes = "json入参，json反参")
    public IntroduceResp objRet(@RequestBody IntroduceReq req) {
        IntroduceResp introduceResp = new IntroduceResp();
        introduceResp.setId(456L);
        introduceResp.setNum(req.getNum());
        introduceResp.setInfo(req.getInfo());
        introduceResp.setFlag(req.getFlag());
        introduceResp.setCreateTime(LocalDateTime.now());
        return introduceResp;
    }

    @PostMapping("/warpRet")
    @ApiOperation(value = "@RequestBody格式入参，包装json反参", notes = "json入参，包装json反参")
    public RespWrapper<IntroduceResp> warpRet(@RequestBody IntroduceReq req) {
        IntroduceResp introduceResp = new IntroduceResp();
        introduceResp.setId(456L);
        introduceResp.setNum(req.getNum());
        introduceResp.setInfo(req.getInfo());
        introduceResp.setFlag(req.getFlag());
        introduceResp.setCreateTime(LocalDateTime.now());
        return new RespWrapper<>(200,"success",introduceResp);
    }

文件上传，swagger页面转换参数格式为文件

    @PostMapping("/file")
    @ApiOperation(value = "文件上传")
    public RespWrapper<String> fileUpload(@RequestPart("file") MultipartFile file) {
        String str = "";
        str += "Content-Type : " + file.getContentType() + ";  ";
        str += "Name : " + file.getName() + ";  ";
        str += "OriginalFilename : " + file.getOriginalFilename();
        return new RespWrapper<>(200, "success", str);
    }

### 访问swagger-ui，调试接口

http://localhost:10004/swagger-ui.html