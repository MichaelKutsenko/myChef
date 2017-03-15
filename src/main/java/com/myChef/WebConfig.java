package com.myChef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Mocart
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger =  LoggerFactory.getLogger(WebConfig.class);
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.warn("=================== Addirng CORS requests support for /** =====================");
        registry.addMapping("/**");
    }

}
