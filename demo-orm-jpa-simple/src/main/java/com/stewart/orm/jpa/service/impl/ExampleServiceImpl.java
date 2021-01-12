package com.stewart.orm.jpa.service.impl;

import com.stewart.orm.jpa.dao.ExampleRepository;
import com.stewart.orm.jpa.entity.Example;
import com.stewart.orm.jpa.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author stewart
 */
@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    @Override
    public List<Example> findAll() {
        return exampleRepository.findAll();
    }

    @Override
    public Example addExample(Example example) {
        return exampleRepository.save(example);
    }

    @Override
    public Example findById(Long id) {
        Optional<Example> example = exampleRepository.findById(id);
        boolean present = example.isPresent();
        if (present) {
            return example.get();
        }
        return null;
    }


    @Override
    public Example updateExample(Example example) {
        return exampleRepository.save(example);
    }

    @Override
    public void deleteById(Long id) {
        exampleRepository.deleteById(id);
    }

}
