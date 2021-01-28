package com.stewart.orm.mybatis.pagehelper.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.stewart.orm.mybatis.pagehelper.entity.Demo;
import com.stewart.orm.mybatis.pagehelper.entity.common.PageParam;
import com.stewart.orm.mybatis.pagehelper.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author stewart
 */
@RestController
@RequestMapping("/orm/mybatis")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/demos")
    public List<Demo> queryAllDemo() {
        return demoService.queryAll();
    }

    @GetMapping("/page")
    public PageInfo<Demo> pageDemo(@RequestBody PageParam pageParam) {
        return demoService.queryPage(pageParam);
    }

    @GetMapping("/page2")
    public Page<Demo> pageDemo2(@RequestBody PageParam pageParam) {
        return demoService.queryPage2(pageParam);
    }

}
