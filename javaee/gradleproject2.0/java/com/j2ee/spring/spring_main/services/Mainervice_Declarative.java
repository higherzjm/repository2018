package com.j2ee.spring.spring_main.services;

/**
 * Created by zjm on 2018/3/7.
 */

public class Mainervice_Declarative {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void myAction(){
        System.out.println("Mainervice_Declarative方法调用:"+name);
    }
}
