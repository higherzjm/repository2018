package com.javase.designpatterns.templatepattern;
/**
 * 大众车
 * @author liaowp
 *
 */
public class Wcar extends CarModel{

    @Override
    protected void start() {
        System.out.println("大众车启动 。。。。。。。。突突突");
    }

    @Override
    protected void stop() {
        System.out.println("大众车停车。。。。。。。。。");
    }
}