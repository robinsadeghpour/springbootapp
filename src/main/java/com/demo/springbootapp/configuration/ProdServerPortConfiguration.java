package com.demo.springbootapp.configuration;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdServerPortConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(9000);
    }
}