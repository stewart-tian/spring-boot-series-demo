package com.stewart.orm.jdbc.dao;

import com.stewart.orm.jdbc.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author stewart
 */
@Component
public class DemoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addDemo(Demo demo) {
        String insertSql = "insert into orm_demo (info,resume) value(?,?)";
        return jdbcTemplate.update(insertSql, demo.getInfo(), demo.getResume());
    }

    public int removeDemoById(Long id) {
        String removeSql = "delete from orm_demo where id = ?";
        return jdbcTemplate.update(removeSql, id);
    }

    public int updateDemoById(Demo demo) {
        String updateSql = "update orm_demo set info = ? , resume = ? where id = ? ";
        return jdbcTemplate.update(updateSql, demo.getInfo(), demo.getResume(), demo.getId());
    }

    public Demo queryDemoById(Long id) {
        String querySql = "select * from orm_demo where id = ?";
        return jdbcTemplate.queryForObject(querySql, new Object[]{id}, (rs, rowNum) -> {
            Demo demo = new Demo();
            demo.setId(rs.getLong("id"));
            demo.setInfo(rs.getString("info"));
            demo.setResume(rs.getString("resume"));
            demo.setDeleteFlag(rs.getInt("delete_flag"));
            demo.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            demo.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
            return demo;
        });
    }

    public List<Demo> queryAllDemo() {
        String queryAllSql = "select * from orm_demo";
        return jdbcTemplate.query(queryAllSql, (rs, rowNum) -> {
            Demo demo = new Demo();
            demo.setId(rs.getLong("id"));
            demo.setInfo(rs.getString("info"));
            demo.setResume(rs.getString("resume"));
            demo.setDeleteFlag(rs.getInt("delete_flag"));
            demo.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            demo.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
            return demo;
        });
    }

    public Integer getDemoNum(){
        String numSql = "select count(*) from orm_demo";
        return jdbcTemplate.queryForObject(numSql,Integer.class);
    }

}
