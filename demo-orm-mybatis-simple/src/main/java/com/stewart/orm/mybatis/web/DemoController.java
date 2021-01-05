package com.stewart.orm.mybatis.web;

import com.stewart.orm.mybatis.entity.Demo;
import com.stewart.orm.mybatis.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author stewart
 */
@RestController
@RequestMapping("/orm/mybatis")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping("/addDemo")
    public int addDemo(@RequestBody Demo demo) {
        return demoService.addDemo(demo);
    }

    @DeleteMapping("/removeDemo")
    public int removeDemoById(Long id) {
        return demoService.removeDemoById(id);
    }

    @PutMapping("/updateDemo")
    public int updateDemoById(@RequestBody Demo demo) {
        return demoService.updateDemoById(demo);
    }

    @GetMapping("/queryDemo")
    public Demo queryDemoById(Long id) {
        return demoService.queryDemoById(id);
    }

    @GetMapping("/queryAll")
    public List<Demo> queryAllDemo() {
        return demoService.queryAllDemo();
    }

}
