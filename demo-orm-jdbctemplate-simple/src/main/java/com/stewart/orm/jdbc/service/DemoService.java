package com.stewart.orm.jdbc.service;


import com.stewart.orm.jdbc.entity.Demo;

import java.util.List;

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

    /**
     * 查询所有
     *
     * @return 所有demo
     */
    List<Demo> queryAllDemo();

    /**
     * 查询数量
     *
     * @return demo数
     */
    Integer getDemoNum();

}
