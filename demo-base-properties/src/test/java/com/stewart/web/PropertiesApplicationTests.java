package com.stewart.web;

import com.stewart.config.properties.ConfigProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author stewart
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesApplicationTests {

    @Autowired
    private ConfigProperties configProperties;

    @Test
    public void testConfigProperties() {
        String str = configProperties.getStr();
        Integer num = configProperties.getNum();
        Assert.assertEquals(str, "String");
        Assert.assertEquals(num, new Integer(1234));
    }

}
