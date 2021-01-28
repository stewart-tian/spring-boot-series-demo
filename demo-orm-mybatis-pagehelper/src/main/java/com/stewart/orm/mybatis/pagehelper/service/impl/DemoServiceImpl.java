package com.stewart.orm.mybatis.pagehelper.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stewart.orm.mybatis.pagehelper.entity.Demo;
import com.stewart.orm.mybatis.pagehelper.entity.common.PageParam;
import com.stewart.orm.mybatis.pagehelper.mapper.DemoMapper;
import com.stewart.orm.mybatis.pagehelper.service.DemoService;
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
    public List<Demo> queryAll() {
        return demoMapper.queryAll();
    }

    @Override
    public PageInfo<Demo> queryPage(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Demo> demos = demoMapper.queryAll();
        return new PageInfo<>(demos);
    }

    @Override
    public Page<Demo> queryPage2(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        return demoMapper.queryPage();
    }

}
