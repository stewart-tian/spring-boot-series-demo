package com.stewart.orm.mybatis.druid.mapper;

import com.stewart.orm.mybatis.druid.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    int save(Demo demo);

    /**
     * 删
     *
     * @param id 主键
     * @return 删数
     */
    int removeById(Long id);

    /**
     * 改
     *
     * @param demo demo
     * @return 改数
     */
    int updateById(Demo demo);

    /**
     * 查
     *
     * @param id 主键
     * @return demo
     */
    Demo queryById(Long id);

    /**
     * 查询所有
     *
     * @return 所有demo
     */
    List<Demo> queryAll();

}
