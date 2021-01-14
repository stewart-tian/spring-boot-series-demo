package com.stewart.base.swagger2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author stewart
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespWrapper<T>  {

    private Integer code;

    private String message;

    private T data;

}
