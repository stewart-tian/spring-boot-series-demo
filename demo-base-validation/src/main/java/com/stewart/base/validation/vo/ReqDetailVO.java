package com.stewart.base.validation.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author stewart
 */
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
