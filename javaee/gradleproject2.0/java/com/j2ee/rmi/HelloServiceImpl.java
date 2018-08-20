package com.j2ee.rmi;

import org.springframework.stereotype.Service;

/**
 * 客服端调用在mavenproject1.0项目
 */
@Service
public class HelloServiceImpl implements IHelloService {

    public String sayHello(String msg) {
        
        System.out.println("服务端接受消息："+msg);
        return "hello-->>"+msg;
    }

}