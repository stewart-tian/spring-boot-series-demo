package com.stewart.thymeleaf.web;

import com.stewart.thymeleaf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stewart
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @GetMapping("/hello")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("welcome", "Hello Spring boot");
        return "hello";
    }

    @GetMapping("/str")
    public String str(ModelMap modelMap) {
        modelMap.addAttribute("developer", "java");
        return "str";
    }

    @GetMapping("/list")
    public String list(ModelMap modelMap) {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "tian", 28, "man"));
        list.add(new User(2L, "java", 20, "man"));
        list.add(new User(3L, "xie", 26, "woman"));
        modelMap.addAttribute("userList", list);
        return "list";
    }

}
