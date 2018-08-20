package com.j2ee.spring.spring_main.services.impl;

import com.j2ee.spring.spring_main.services.InitializingService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("initializingService")
public class InitializingServiceImpl implements InitializingService, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        
        System.out.println("call InitializingBean---afterPropertiesSet");
    }

    @Override
    public void say() {
        
        System.out.println("call say");
    }

}