package com.sabulous.contractRateManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {
    
    @Autowired
    private static ApplicationContext appCtx;

    public static ApplicationContext getApplicationContext() {
        return appCtx;
    }
}