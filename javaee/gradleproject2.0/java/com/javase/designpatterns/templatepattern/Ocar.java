package com.javase.designpatterns.templatepattern;

public class Ocar extends CarModel {

    @Override
    protected void start() {
        System.out.println("奥迪  无匙启动 突突突");
    }

    @Override
    protected void stop() {
        System.out.println("奥迪  停车              ");
    }
}
