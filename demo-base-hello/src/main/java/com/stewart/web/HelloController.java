package com.stewart.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stewart
 */
@RestController
@RequestMapping("/base")
public class HelloController {

    @GetMapping("/hello")
    public String hello(String name) {
        if (name == null || "".equals(name)) {
            return "Hello";
        }
        return "Hello " + name;
    }

    @GetMapping("/demo")
    public String demo() {
        return "Hello";
    }

}
