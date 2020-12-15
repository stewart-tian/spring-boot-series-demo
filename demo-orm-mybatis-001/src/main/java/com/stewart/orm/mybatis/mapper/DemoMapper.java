package com.stewart.orm.mybatis.mapper;

import com.stewart.orm.mybatis.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author stewart
 */
@Mapper
public interface DemoMapper {

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
     * @return 改数
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

