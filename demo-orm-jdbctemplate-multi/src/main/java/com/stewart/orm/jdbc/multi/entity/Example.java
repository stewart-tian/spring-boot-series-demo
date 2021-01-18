package com.stewart.orm.jdbc.multi.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author stewart
 */
@Data
public class Example {

    private Long id;

    private String info;

    private String resume;

    private Integer deleteFlag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
