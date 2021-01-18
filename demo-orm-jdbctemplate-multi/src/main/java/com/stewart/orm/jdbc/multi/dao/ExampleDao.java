package com.stewart.orm.jdbc.multi.dao;

import com.stewart.orm.jdbc.multi.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author stewart
 */
@Component
public class ExampleDao {

    @Autowired
    private JdbcTemplate secondJdbcTemplate;

    public int addExample(Example example) {
        String insertSql = "insert into orm_example (info,resume) value(?,?)";
        return secondJdbcTemplate.update(insertSql, example.getInfo(), example.getResume());
    }

    public int removeExampleById(Long id) {
        String removeSql = "delete from orm_example where id = ?";
        return secondJdbcTemplate.update(removeSql, id);
    }

    public int updateExampleById(Example example) {
        String updateSql = "update orm_example set info = ? , resume = ? where id = ? ";
        return secondJdbcTemplate.update(updateSql, example.getInfo(), example.getResume(), example.getId());
    }

    public Example queryExampleById(Long id) {
        String querySql = "select * from orm_example where id = ?";
        return secondJdbcTemplate.queryForObject(querySql, new Object[]{id}, (rs, rowNum) -> {
            Example example = new Example();
            example.setId(rs.getLong("id"));
            example.setInfo(rs.getString("info"));
            example.setResume(rs.getString("resume"));
            example.setDeleteFlag(rs.getInt("delete_flag"));
            example.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            example.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
            return example;
        });
    }

    public List<Example> queryAllExample() {
        String queryAllSql = "select * from orm_example";
        return secondJdbcTemplate.query(queryAllSql, (rs, rowNum) -> {
            Example example = new Example();
            example.setId(rs.getLong("id"));
            example.setInfo(rs.getString("info"));
            example.setResume(rs.getString("resume"));
            example.setDeleteFlag(rs.getInt("delete_flag"));
            example.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            example.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
            return example;
        });
    }

    public Integer getExampleNum() {
        String numSql = "select count(*) from orm_example";
        return secondJdbcTemplate.queryForObject(numSql, Integer.class);
    }

}
