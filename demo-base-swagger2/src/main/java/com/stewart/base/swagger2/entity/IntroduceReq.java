package com.stewart.base.swagger2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author stewart
 */
@Data
@ApiModel
public class IntroduceReq {

    @ApiModelProperty(name = "数字",value = "数字值",example = "456")
    private Long num;

    @ApiModelProperty(name = "字符串",value = "字符串",example = "Swagger介绍")
    private String info;

    @ApiModelProperty(name = "标志",value = "标志值",example = "true")
    private Boolean flag;

}
