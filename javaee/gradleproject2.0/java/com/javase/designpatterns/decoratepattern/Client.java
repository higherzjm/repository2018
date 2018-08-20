package com.javase.designpatterns.decoratepattern;

/**
 * 装饰模式
 */
public  class Client
{
       public  static void main(String args[])
       {
              Component component,component2;  //使用抽象构件定义
              component = new Window(); //定义具体构件
              component2 = new  ScrollBarDecorator(component); //定义装饰后的构件
              //component2 = new  BlackBorderDecorator(component);
              component2.display();
       }
}