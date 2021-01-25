package com.stewart.base.validation.vo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author stewart
 */
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
