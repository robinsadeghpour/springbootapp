package com.demo.springbootapp.configuration;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevServerPortConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(8000);
    }
}
