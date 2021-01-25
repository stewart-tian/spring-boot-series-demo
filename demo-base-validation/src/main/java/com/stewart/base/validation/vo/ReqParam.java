package com.stewart.base.validation.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author stewart
 */
@Data
public class ReqParam {

    @NotNull
    private Long id;

    @NotBlank
    private String info;

}
