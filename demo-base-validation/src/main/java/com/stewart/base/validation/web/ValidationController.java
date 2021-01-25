package com.stewart.base.validation.web;

import com.stewart.base.validation.vo.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author stewart
 */
@Validated
@RestController
@RequestMapping("/base/validation")
public class ValidationController {

    /**
     * 对非model参数化的校验，注解使用在参数前，控制器类上必须加上@Validated校验才生效，加在方法参数前无效
     */
    @GetMapping("/str")
    public String validStrParam(@NotEmpty(message = "字符串必输") @RequestParam("str") String str) {
        return "String valid pass : " + str;
    }

    @GetMapping("/{path}")
    public String validPathVar(@Length(min = 3, max = 20) @PathVariable("path") String path) {
        return "Path valid pass : " + path;
    }

    /**
     * model参数校验，@Validated注解需加在model参数前，加载控制器类上无效
     */
    @PostMapping("/obj")
    public String validObjParam(@Validated @RequestBody ReqDetailVO reqDetailVO) {
        return "Object valid pass : " + reqDetailVO.toString();
    }

    @PostMapping("/list-obj")
    public String validListInObj(@Validated @RequestBody ReqListVO reqListVO) {
        return "List in Object valid pass : " + reqListVO.toString();
    }

    /**
     * list参数校验和list内置参数校验，均需在控制器类上加@Validated注解使用
     */
    @PostMapping("/list")
    public String validListParam(@RequestBody @Size(min = 2, max = 99) List<@Valid ReqParam> reqParams) {
        return "List in Object valid pass : " + reqParams.toString();
    }

    @PostMapping("/obj-obj")
    public String validObjInObj(@Validated @RequestBody ReqVO reqVO) {
        return "Object in Object pass : " + reqVO.toString();
    }

}
