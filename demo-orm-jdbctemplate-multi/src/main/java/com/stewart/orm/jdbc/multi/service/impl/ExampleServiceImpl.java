package com.stewart.orm.jdbc.multi.service.impl;

import com.stewart.orm.jdbc.multi.dao.ExampleDao;
import com.stewart.orm.jdbc.multi.entity.Example;
import com.stewart.orm.jdbc.multi.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author stewart
 */
@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleDao exampleDao;

    @Override
    public int addExample(Example example) {
        return exampleDao.addExample(example);
    }

    @Override
    public int removeExampleById(Long id) {
        return exampleDao.removeExampleById(id);
    }

    @Override
    public int updateExampleById(Example example) {
        return exampleDao.updateExampleById(example);
    }

    @Override
    public Example queryExampleById(Long id) {
        return exampleDao.queryExampleById(id);
    }

    @Override
    public List<Example> queryAllExample() {
        return exampleDao.queryAllExample();
    }

    @Override
    public Integer getExampleNum() {
        return exampleDao.getExampleNum();
    }

}
