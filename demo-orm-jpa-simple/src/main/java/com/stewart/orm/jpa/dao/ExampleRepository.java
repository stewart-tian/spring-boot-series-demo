package com.stewart.orm.jpa.dao;

import com.stewart.orm.jpa.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author stewart
 */
public interface ExampleRepository extends JpaRepository<Example, Long> {

}
