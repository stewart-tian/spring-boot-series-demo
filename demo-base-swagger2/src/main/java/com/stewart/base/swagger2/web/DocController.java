package com.stewart.base.swagger2.web;

import com.stewart.base.swagger2.entity.DocDemo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author stewart
 */
@RestController
@RequestMapping("/base/doc")
@Api(tags = "Swagger2功能测试类")
public class DocController {

    @GetMapping("/hello")
    @ApiOperation(value = "方法简述", notes = "方法详细说明")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/demo")
    @ApiOperation(value = "模拟新增数据")
    public DocDemo save(@RequestBody DocDemo demo) {
        demo.setCreateTime(LocalDateTime.now());
        return demo;
    }

    @PutMapping("/demo")
    @ApiOperation(value = "模拟修改数据")
    public DocDemo update(@RequestBody DocDemo demo) {
        return new DocDemo();
    }

    @DeleteMapping("/demo")
    @ApiOperation(value = "模拟删除数据")
    public Boolean delete(@RequestParam("id") Long id) {
        return Boolean.TRUE;
    }

    @GetMapping("/demo")
    @ApiOperation(value = "模拟查询单条数据")
    public DocDemo query(@RequestParam("id") Long id) {
        DocDemo demo = new DocDemo();
        demo.setId(id);
        demo.setInfo("查询");
        demo.setFlag(true);
        demo.setCreateTime(LocalDateTime.now());
        return demo;
    }

    @GetMapping("/demos")
    @ApiOperation(value = "模拟查询列表数据")
    public List<DocDemo> queryAll() {
        List<DocDemo> list = new ArrayList<>();
        list.add(new DocDemo(111L, "Doc1", true, LocalDateTime.now()));
        list.add(new DocDemo(222L, "Doc2", true, LocalDateTime.now()));
        list.add(new DocDemo(333L, "Doc3", true, LocalDateTime.now()));
        list.add(new DocDemo(444L, "Doc4", true, LocalDateTime.now()));
        list.add(new DocDemo(555L, "Doc5", true, LocalDateTime.now()));
        list.add(new DocDemo(666L, "Doc6", true, LocalDateTime.now()));
        return list;
    }


}
