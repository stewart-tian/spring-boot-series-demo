package com.stewart.orm.mybatis.web;

import com.stewart.orm.mybatis.entity.Demo;
import com.stewart.orm.mybatis.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stewart
 */
@RestController
@RequestMapping("/orm/mybatis")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/addDemo")
    public int addDemo(Demo demo) {
        return demoService.addDemo(demo);
    }

    @GetMapping("/removeDemo")
    public int removeDemoById(Long id) {
        return demoService.removeDemoById(id);
    }

    @GetMapping("/updateDemo")
    public int updateDemoById(Demo demo) {
        return demoService.updateDemoById(demo);
    }

    @GetMapping("/queryDemo")
    public Demo queryDemoById(Long id) {
        return demoService.queryDemoById(id);
    }

}
