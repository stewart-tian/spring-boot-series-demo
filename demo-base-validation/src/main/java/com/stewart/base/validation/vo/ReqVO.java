package com.stewart.base.validation.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author stewart
 */
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
