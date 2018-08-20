package com.javase.designpatterns.chainofresponsibilitypattern.example1;

import org.junit.Test;

/**
 * 责任链设计模式
 *
 * @author lzy
 */
public class MainClass {

    public static void main(String[] args) {

    }

    @Test
    public void test1() {
        Request request = new Request.Builder().setName("张三").setDays(2)
                .setReason("事假").build();
        ChainOfResponsibilityClient client = new ChainOfResponsibilityClient();
        Result result = client.execute(request);
        System.out.println("结果：" + result.toString());
    }

    //添加一个责任人
    @Test
    public void test2() {
        Request request = new Request.Builder().setName("张三").setDays(5)
                .setReason("事假").build();
        ChainOfResponsibilityClient client = new ChainOfResponsibilityClient();
        client.addRatifys(new CustomInterceptor());
        Result result = client.execute(request);
        System.out.println("结果：" + result.toString());
    }
}

