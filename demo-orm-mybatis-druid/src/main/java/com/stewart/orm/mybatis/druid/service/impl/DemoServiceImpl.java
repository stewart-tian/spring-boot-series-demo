package com.stewart.orm.mybatis.druid.service.impl;

import com.stewart.orm.mybatis.druid.entity.Demo;
import com.stewart.orm.mybatis.druid.mapper.DemoMapper;
import com.stewart.orm.mybatis.druid.service.DemoService;
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
    public int save(Demo demo) {
        return demoMapper.save(demo);
    }

    @Override
    public int removeById(Long id) {
        return demoMapper.removeById(id);
    }

    @Override
    public int updateById(Demo demo) {
        return demoMapper.updateById(demo);
    }

    @Override
    public Demo queryById(Long id) {
        return demoMapper.queryById(id);
    }

    @Override
    public List<Demo> queryAll() {
        return demoMapper.queryAll();
    }

}
