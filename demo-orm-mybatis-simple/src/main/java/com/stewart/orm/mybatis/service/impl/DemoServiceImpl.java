package com.stewart.orm.mybatis.service.impl;

import com.stewart.orm.mybatis.entity.Demo;
import com.stewart.orm.mybatis.mapper.DemoMapper;
import com.stewart.orm.mybatis.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author stewart
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public int addDemo(Demo demo) {
        return demoMapper.addDemo(demo);
    }

    @Override
    public int removeDemoById(Long id) {
        return demoMapper.removeDemoById(id);
    }

    @Override
    public int updateDemoById(Demo demo) {
        return demoMapper.updateDemoById(demo);
    }

    @Override
    public Demo queryDemoById(Long id) {
        return demoMapper.queryDemoById(id);
    }

    @Override
    public List<Demo> queryAllDemo() {
        return demoMapper.queryAllDemo();
    }
}
