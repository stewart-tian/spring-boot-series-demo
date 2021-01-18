package com.stewart.orm.jdbc.multi.web;

import com.stewart.orm.jdbc.multi.entity.Demo;
import com.stewart.orm.jdbc.multi.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author stewart
 */
@RestController
@RequestMapping("/orm/jdbc")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping("/demo")
    public int addDemo(@RequestBody Demo demo) {
        return demoService.addDemo(demo);
    }

    @DeleteMapping("/demo")
    public int removeDemoById(Long id) {
        return demoService.removeDemoById(id);
    }

    @PutMapping("/demo")
    public int updateDemoById(@RequestBody Demo demo) {
        return demoService.updateDemoById(demo);
    }

    @GetMapping("/demo")
    public Demo queryDemoById(Long id) {
        return demoService.queryDemoById(id);
    }

    @GetMapping("/demos")
    public List<Demo> queryAllDemo() {
        return demoService.queryAllDemo();
    }

    @GetMapping("/demo/num")
    public Integer getDemoNum() {
        return demoService.getDemoNum();
    }

}
