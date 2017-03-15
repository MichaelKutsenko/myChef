package com.myChef.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Mocart
 */
@Component
public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent>{
    private static final Logger logger =  LoggerFactory.getLogger(ApplicationStartListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.warn("=====================================================================================");
        logger.warn("======================== Application started successfully ===========================");
        logger.warn("=====================================================================================");
        logger.warn("");
    }
}
