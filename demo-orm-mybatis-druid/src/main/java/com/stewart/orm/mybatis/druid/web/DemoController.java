package com.stewart.orm.mybatis.druid.web;

import com.stewart.orm.mybatis.druid.entity.Demo;
import com.stewart.orm.mybatis.druid.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author stewart
 */
@RestController
@RequestMapping("/orm/mybatis/druid")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping("/demo")
    public int save(@RequestBody Demo demo) {
        return demoService.save(demo);
    }

    @DeleteMapping("/demo")
    public int removeById(Long id) {
        return demoService.removeById(id);
    }

    @PutMapping("/demo")
    public int updateById(@RequestBody Demo demo) {
        return demoService.updateById(demo);
    }

    @GetMapping("/demo")
    public Demo queryById(Long id) {
        return demoService.queryById(id);
    }

    @GetMapping("/demos")
    public List<Demo> queryAll() {
        return demoService.queryAll();
    }

}
