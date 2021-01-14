package com.stewart.base.swagger2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author stewart
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "文档demo", description = "文档description")
public class DocDemo {

    @ApiModelProperty(name = "编号", value = "编号", example = "666")
    private Long id;

    @ApiModelProperty(name = "信息", value = "信息", example = "Swagger文档")
    private String info;

    @ApiModelProperty(name = "标志", value = "标志", example = "true")
    private Boolean flag;

    @ApiModelProperty(name = "创建时间", value = "创建时间", hidden = true)
    private LocalDateTime createTime;

}

