package com.j2ee.spring.spring_main.services;


import org.springframework.stereotype.Service;

/**
 * Created by zjm on 2018/3/7.
 */
@Service("mainserviceannotation")
public class MainService_Annotation {
    public void myAction(){
        System.out.println("MainService_Annotation方法调用");
    }
}
