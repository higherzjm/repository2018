package com.j2ee.spring.spring_main;

import com.j2ee.spring.spring_main.services.InitializingService;
import com.j2ee.spring.spring_main.services.MainService_Annotation;
import com.j2ee.spring.spring_main.services.Mainervice_Declarative;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 使用mainh函数初始化spring配置文件
 */
public class MainClass {
    public static  void main(String[] args){

    }

    /**
     * 调用初始化的实例测试
     */
    @Test
    public void test1(){
        BeanFactory factory= new FileSystemXmlApplicationContext("classpath:spring_main.xml");
        Mainervice_Declarative mainervice_declarative=(Mainervice_Declarative)factory.getBean("MainerviceDeclarative_bean");
        System.out.println(mainervice_declarative.getName());
        mainervice_declarative.myAction();

        //无法调用注解的实例
        MainService_Annotation mainService_annotation=(MainService_Annotation)factory.getBean("mainserviceannotation");
        mainService_annotation.myAction();
    }

    /**
     * InitializingBean----afterPropertiesSet测试
     */
    @Test
    public void test2(){
        BeanFactory factory= new FileSystemXmlApplicationContext("classpath:spring_main.xml");
        InitializingService initializingService=(InitializingService)factory.getBean("initializingService");
        initializingService.say();

        MainService_Annotation mainService_annotation=(MainService_Annotation)factory.getBean("mainserviceannotation");
        mainService_annotation.myAction();
    }
}
