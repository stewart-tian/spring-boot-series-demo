package com.stewart.orm.jpa.web;

import com.stewart.orm.jpa.entity.Example;
import com.stewart.orm.jpa.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author stewart
 */
@RestController
@RequestMapping("/orm/jpa")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping("/example")
    public Example getExample(Long id) {
        return exampleService.findById(id);
    }

    @GetMapping("/examples")
    public List<Example> findAllExample() {
        return exampleService.findAll();
    }

    @PostMapping("/example")
    public Example addExample(@RequestBody Example example) {
        return exampleService.addExample(example);
    }

    @PutMapping("/example")
    public Example updateExample(@RequestBody Example example) {
        return exampleService.updateExample(example);
    }

    @DeleteMapping("/example")
    public void deleteExample(Long id) {
        exampleService.deleteById(id);
    }

}
