package com.javase.designpatterns.templatepattern;

/**
 * 模版模式
 */
public class Client {
    public static void main(String[] args) {
        CarModel wcar=new Wcar();//家里的第一辆车，作为用户的我们并不需要关注车怎么启动的.子类变量变为父类。多态
        wcar.excet();
        
        //突然家里需要第二辆车了   奥迪我们也不需要关注他怎么生产启动的
        CarModel ocar=new Ocar();
        ocar.excet();
    }
}