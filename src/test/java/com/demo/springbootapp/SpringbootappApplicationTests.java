package com.demo.springbootapp;

import com.demo.springbootapp.repository.UserRepository;
import com.demo.springbootapp.support.Validator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootappApplicationTests {

    @Autowired
    private Validator validator;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        assertNotNull(validator);
        assertNotNull(userRepository);
    }
}
