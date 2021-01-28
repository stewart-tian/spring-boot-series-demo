package com.stewart.orm.mybatis.pagehelper.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.stewart.orm.mybatis.pagehelper.entity.Demo;
import com.stewart.orm.mybatis.pagehelper.entity.common.PageParam;

import java.util.List;

/**
 * @author stewart
 */
public interface DemoService {

    /**
     * 查询所有
     *
     * @return 所有demo
     */
    List<Demo> queryAll();

    /**
     * 分页查询
     *
     * @param pageParam 分页设置参数
     * @return 分页数据
     */
    PageInfo<Demo> queryPage(PageParam pageParam);

    /**
     * 分页查询
     *
     * @param pageParam 分页设置参数
     * @return 分页数据
     */
    Page<Demo> queryPage2(PageParam pageParam);

}
