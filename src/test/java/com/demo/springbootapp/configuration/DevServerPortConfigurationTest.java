package com.demo.springbootapp.configuration;

import com.demo.springbootapp.SpringbootappApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootappApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
class DevServerPortConfigurationTest {
    private final static int EXPECTED_PORT = 8000;

    @Autowired
    ServletWebServerApplicationContext server;

    @Test
    public void givenFixedPortAsServerPort_whenReadServerProps_thenGetThePort() {
        int port = server.getWebServer().getPort();

        assertEquals(EXPECTED_PORT, port);
    }
}