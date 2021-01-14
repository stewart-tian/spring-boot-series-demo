package com.stewart.base.swagger2.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author stewart
 */
@Data
public class IntroduceResp {

    private Long id;

    private Long num;

    private String info;

    private Boolean flag;

    private LocalDateTime createTime;

}
