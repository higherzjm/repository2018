package com.javase.dynamicproxy.example1;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * 实现类代理
 */
public class MainDanamicProxy {
    @Test
    public void  test(){
        Calculator calculator=new CalculatorImpl();
        LogHandler logHandler=new LogHandler(calculator);
        Calculator proxy= (Calculator) Proxy.newProxyInstance(calculator.getClass().getClassLoader(),
                calculator.getClass().getInterfaces(),logHandler);
        int sum=proxy.add(11,22);
        System.out.println("sum:"+sum);
    }

    @Test
    public void  test2 (){

        Calculator newObject=new Calculator() {
            @Override
            public int add(int a, int b) {
                return (a+b)*2000;
            }
        };

        Calculator calculator = LogHandler.newMapperProxy(Calculator.class,newObject);
        int sum=calculator.add(11,22);
        System.out.println("sum:"+sum);
    }

}
