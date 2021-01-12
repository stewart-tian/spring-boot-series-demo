package com.stewart.orm.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author stewart
 */
@Data
@Entity
public class Example implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String info;

    private Boolean flag;

    private LocalDateTime createTime;

}
