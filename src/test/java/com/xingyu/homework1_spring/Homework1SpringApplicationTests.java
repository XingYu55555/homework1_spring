package com.xingyu.homework1_spring;

import com.xingyu.homework1_spring.services.KeyValueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class Homework1SpringApplicationTests {

    @Autowired
    private KeyValueService KeyValueService;

    @Test
    public void testPut() {
        String key = "key1";
        String value = "value1";
        String result = KeyValueService.put(key, value);

    }

    @Test
    public void testGet() {
        String key = "key1";
        String result = KeyValueService.get(key);
    }

    @Test
    public void testDelete() {
        String key = "key1";
        String result = KeyValueService.delete(key);
    }

}
