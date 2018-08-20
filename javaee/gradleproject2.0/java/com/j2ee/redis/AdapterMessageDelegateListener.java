package com.j2ee.redis;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Component("adapterMessageDelegateListener")
public class AdapterMessageDelegateListener {

    //监听Redis消息
    public void handleMessage(Serializable message){
        System.out.println("redis消息队列接收消息");
        if(message instanceof MessageVo){
            MessageVo messageVo = (MessageVo) message;
            System.out.println(messageVo.getKey()+":"+messageVo.getDate());
            Map<String,List<String>> webmatids= messageVo.getWebsitematids();
            for (Map.Entry<String,List<String>> data:webmatids.entrySet()){
                System.out.println(data.getKey());
                System.out.println(data.getValue());
            }

        }
    }

}