package com.j2ee.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.*;

@Controller
@RequestMapping(value ="/send_jsm_merssagecontroller")
public class Send_JMS_MessageController {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource(name= "queueDestination")//因为可能配置多个目的地，所以使用resource name进行区分
    Destination destination;

    @RequestMapping(value = "sendmsg",produces = "application/json;charset=utf-8")
    @ResponseBody
    public  String sendMessage(){
       try{
           String message="";
           for(int i=0;i<100;i++){
               message="测试发送消息:"+i;
               sendMessage(message);
               System.out.println("发送消息："+message);
           }
           return "发送成功";
       }catch (Exception e){
           e.printStackTrace();
           return "发送失败";
       }

    }
    public void sendMessage(final String message) {
        //使用JmsTemplate发送消息
        jmsTemplate.send(destination,new MessageCreator() {
            //创建消息
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                return textMessage;
            }
        });
        System.out.println("发送消息："+message);
    }
}