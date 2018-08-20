package com.j2ee.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppProducer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_jms.xml");
        ProducerService service = context.getBean(ProducerService.class);
        for(int i=0;i<100;i++){
            service.sendMessage("test"+i);
        }
        //会自动清理资源
        ((AbstractApplicationContext) context).close();
    }
}