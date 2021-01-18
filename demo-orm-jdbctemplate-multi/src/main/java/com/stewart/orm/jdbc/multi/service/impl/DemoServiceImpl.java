package com.stewart.orm.jdbc.multi.service.impl;

import com.stewart.orm.jdbc.multi.dao.DemoDao;
import com.stewart.orm.jdbc.multi.entity.Demo;
import com.stewart.orm.jdbc.multi.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author stewart
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;

    @Override
    public int addDemo(Demo demo) {
        return demoDao.addDemo(demo);
    }

    @Override
    public int removeDemoById(Long id) {
        return demoDao.removeDemoById(id);
    }

    @Override
    public int updateDemoById(Demo demo) {
        return demoDao.updateDemoById(demo);
    }

    @Override
    public Demo queryDemoById(Long id) {
        return demoDao.queryDemoById(id);
    }

    @Override
    public List<Demo> queryAllDemo() {
        return demoDao.queryAllDemo();
    }

    @Override
    public Integer getDemoNum() {
        return demoDao.getDemoNum();
    }
}
