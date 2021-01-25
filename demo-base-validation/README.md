# spring-boot-demo-base-validation

> demo-base-validation : spring-boot-web 请求参数校验

## 一、配置

### 1.1、引入依赖

spring-boot-web-2.3.x中将validation移出，需要单独引用spring-boot-starter-validation

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

### 1.2、非model参数处理

```java

@Validated
@RestController
@RequestMapping("/base/validation")
public class ValidationController {

    /**
     * 对非model参数化的校验，注解使用在参数前，控制器类上必须加上@Validated校验才生效，加在方法参数前无效
     */
    @GetMapping("/str")
    public String validStrParam(@NotEmpty(message = "字符串必输") @RequestParam("str") String str) {
        return "String valid pass : " + str;
    }

    @GetMapping("/{path}")
    public String validPathVar(@Length(min = 3, max = 20) @PathVariable("path") String path) {
        return "Path valid pass : " + path;
    }
}
```

### 1.3、model参数

    /**
     * model参数校验，@Validated注解需加在model参数前，加载控制器类上无效
     */
    @PostMapping("/obj")
    public String validObjParam(@Validated @RequestBody ReqDetailVO reqDetailVO){
            return"Object valid pass : "+reqDetailVO.toString();
    }

### 1.4、list参数校验

#### 1.4.1 方法入参上加@Validated注解、将list作为model对象属性

控制器方法

    @PostMapping("/list-obj")
    public String validListInObj(@Validated @RequestBody ReqListVO reqListVO) {
        return "List in Object valid pass : " + reqListVO.toString();
    }

model对象

    @Data
    public class ReqListVO {
        /**
         * 嵌套校验使用@Valid，不添加该注解，嵌套的对象校验注解不生效
         */
        @Valid
        @NotNull
        @Size(min = 2, max = 99)
        List<ReqParam> reqParams;
    }

list内对象

    @Data
    public class ReqParam {
    
        @NotNull
        private Long id;
    
        @NotBlank
        private String info;
    
    }

#### 1.4.2、list泛型内添加@Valid注解

    /**
     * list参数校验和list内置参数校验，均需在控制器类上加@Validated注解使用
     */
    @PostMapping("/list")
    public String validListParam(@RequestBody @Size(min = 2, max = 99) List<@Valid ReqParam> reqParams) {
        return "List in Object valid pass : " + reqParams.toString();
    }

### 1.5、嵌套校验

控制器内请求方法

    @PostMapping("/obj-obj")
    public String validObjInObj(@Validated @RequestBody ReqVO reqVO) {
        return "Object in Object pass : " + reqVO.toString();
    }

入参model对象

    @Data
    public class ReqVO {
    
        @NotNull
        private Long id;
    
        @NotBlank
        private String name;
    
        @NotNull
        @Range(min = 0, max = 160)
        private Integer age;
    
        /**
         * 通过@Valid嵌套对象校验，不添加@NotNull，当对象为null时，对象内的属性也不校验
         */
        @Valid
        @NotNull
        private ReqDetailVO reqDetailVO;
    
        @Valid
        @NotNull
        private List<ReqParam> reqParams;
    
    }

嵌入对象

    @Data
    public class ReqDetailVO {
    
        @NotNull
        private Long id;
    
        /**
         * 必须为null
         */
        @Null
        private String nullStr;
    
        /**
         * 可以为null，部位null时必须为true
         */
        @AssertTrue
        private Boolean trueFlag;
    
        /**
         * 必须为false
         */
        @NotNull
        @AssertFalse
        private Boolean falseFlag;
    
        /**
         * 可为null，取值范围
         */
        @DecimalMin(value = "9.9")
        @DecimalMax(value = "999.99")
        private String decimalNum;
    
        /**
         * 不可为null，取值范围
         */
        @NotNull
        @DecimalMin(value = "-9.9")
        @DecimalMax(value = "999.99")
        private BigDecimal bigDecimal;
    
        /**
         * 不可为null，取值范围
         */
        @NotNull
        @DecimalMin(value = "9.9")
        @DecimalMax(value = "999.99")
        private Double doubleDecimal;
    
        /**
         * 不为null时必须为数据类型数据，限定整数位长度和小数位长度
         */
        @Digits(integer = 8, fraction = 4)
        private String strDigits;
    
        /**
         * 使用整型类型数据时，小数位即使超过了长度也没影响，数据将被截取整数部分赋值
         */
        @Digits(integer = 8, fraction = 4)
        private Long longDigits;
    
        @Digits(integer = 8, fraction = 4)
        private BigDecimal decimalDigits;
    
        @Digits(integer = 8, fraction = 4)
        private Double doubleDigits;
    
        /**
         * 最小值，最大值 注解类参数为long类型
         */
        @Min(9)
        @Max(99)
        private Integer num;
    
        /**
         * 邮箱校验，可自定义正则
         */
        @Email
        private String email;
    
        /**
         * 长度在指定范围
         */
        @Length(min = 3, max = 99)
        private String name;
    
        /**
         * 字符串不能为null, 字符串trim()后也不能等于“”
         */
        @NotBlank
        private String blank;
    
        /**
         * 不能为null，集合、数组、map等size()不能为0；字符串trim()后可以等于“”，不能直接为“”
         */
        @NotEmpty
        private String empty;
    
        /**
         * 取值范围 @Min和@Max的结合 min和max属性为long类型
         */
        @Range(min = 9, max = 999)
        private String range;
    
        /**
         * 正则匹配
         */
        @Pattern(regexp = "[\\s\\S]*")
        private String reg;
    
        /**
         * 当前时间之后的时间 不能等于当前LocalDate
         */
        @Future
        private LocalDate futureDate;
    
        /**
         * 当前时间之前的时间
         */
        @Past
        private LocalDate pastData;
    
        /**
         * 当前时间之后的时间
         */
        @Future
        private LocalDateTime futureDateTime;
    
        /**
         * 当前时间之前的时间
         */
        @Past
        private LocalDateTime pastDataTime;
    
        /**
         * 集合、数组、map等的size()值必须在指定范围内,可用于字符串的长度
         */
        @Size(min = 9, max = 99)
        private String size;
    
        /**
         * 必须是一个URL 协议+域名+[端口]
         */
        @URL
        private String url;
    
    }

嵌入list泛型对象

    @Data
    public class ReqVO {
    
        @NotNull
        private Long id;
    
        @NotBlank
        private String name;
    
        @NotNull
        @Range(min = 0, max = 160)
        private Integer age;
    
        /**
         * 通过@Valid嵌套对象校验，不添加@NotNull，当对象为null时，对象内的属性也不校验
         */
        @Valid
        @NotNull
        private ReqDetailVO reqDetailVO;
    
        @Valid
        @NotNull
        private List<ReqParam> reqParams;
    
    }