package com.stewart.orm.mybatis.pagehelper.mapper;

import com.github.pagehelper.Page;
import com.stewart.orm.mybatis.pagehelper.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author stewart
 */
@Mapper
public interface DemoMapper {

    /**
     * 查询所有
     *
     * @return 所有demo
     */
    List<Demo> queryAll();

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    Page<Demo> queryPage();

}

