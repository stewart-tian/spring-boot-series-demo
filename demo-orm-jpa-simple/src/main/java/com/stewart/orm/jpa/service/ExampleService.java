package com.stewart.orm.jpa.service;

import com.stewart.orm.jpa.entity.Example;

import java.util.List;

/**
 * @author stewart
 */
public interface ExampleService {

    /**
     * 查所有
     *
     * @return 所有Example
     */
    List<Example> findAll();

    /**
     * 添加
     *
     * @param example 添加的数据
     * @return example
     */
    Example addExample(Example example);

    /**
     * 查
     *
     * @param id id
     * @return example
     */
    Example findById(Long id);

    /**
     * 更新
     *
     * @param example example 更新的数据
     * @return example
     */
    Example updateExample(Example example);

    /**
     * 删除
     *
     * @param id id
     */
    void deleteById(Long id);

}
