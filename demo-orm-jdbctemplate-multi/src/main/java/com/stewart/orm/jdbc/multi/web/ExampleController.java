package com.stewart.orm.jdbc.multi.web;

import com.stewart.orm.jdbc.multi.entity.Example;
import com.stewart.orm.jdbc.multi.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author stewart
 */
@RestController
@RequestMapping("/orm/jdbc")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @PostMapping("/example")
    public int addExample(@RequestBody Example example) {
        return exampleService.addExample(example);
    }

    @DeleteMapping("/example")
    public int removeExampleById(Long id) {
        return exampleService.removeExampleById(id);
    }

    @PutMapping("/example")
    public int updateExampleById(@RequestBody Example example) {
        return exampleService.updateExampleById(example);
    }

    @GetMapping("/example")
    public Example queryExampleById(Long id) {
        return exampleService.queryExampleById(id);
    }

    @GetMapping("/examples")
    public List<Example> queryAllExample() {
        return exampleService.queryAllExample();
    }

    @GetMapping("/example/num")
    public Integer getExampleNum() {
        return exampleService.getExampleNum();
    }

}
