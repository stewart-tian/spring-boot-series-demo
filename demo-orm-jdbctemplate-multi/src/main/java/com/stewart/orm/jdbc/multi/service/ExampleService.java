package com.stewart.orm.jdbc.multi.service;


import com.stewart.orm.jdbc.multi.entity.Example;

import java.util.List;

/**
 * @author stewart
 */
public interface ExampleService {

    /**
     * 增
     *
     * @param example example
     * @return 增数
     */
    int addExample(Example example);

    /**
     * 删
     *
     * @param id 主键
     * @return 删数
     */
    int removeExampleById(Long id);

    /**
     * 改
     *
     * @param example example
     * @return 更新数
     */
    int updateExampleById(Example example);

    /**
     * 查
     *
     * @param id 主键
     * @return Example
     */
    Example queryExampleById(Long id);

    /**
     * 查询所有
     *
     * @return 所有Example
     */
    List<Example> queryAllExample();

    /**
     * 查询数量
     *
     * @return Example数
     */
    Integer getExampleNum();

}
