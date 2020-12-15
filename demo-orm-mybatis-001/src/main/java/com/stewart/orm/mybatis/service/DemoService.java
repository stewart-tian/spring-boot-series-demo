package com.stewart.orm.mybatis.service;

import com.stewart.orm.mybatis.entity.Demo;

/**
 * @author stewart
 */
public interface DemoService {

    /**
     * 增
     *
     * @param demo demo
     * @return 增数
     */
    int addDemo(Demo demo);

    /**
     * 删
     *
     * @param id 主键
     * @return 删数
     */
    int removeDemoById(Long id);

    /**
     * 改
     *
     * @param demo demo
     * @return 更新数
     */
    int updateDemoById(Demo demo);

    /**
     * 查
     *
     * @param id 主键
     * @return demo
     */
    Demo queryDemoById(Long id);

}
