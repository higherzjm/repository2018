package com.j2ee.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//http://localhost:9001/gradleproject1.0/send_redis_merssagecontroller/sendmsg.do
@Controller
@RequestMapping(value ="/send_redis_merssagecontroller")
public class Send_REDIS_MessageController {

    @Resource(name="redisTemplate")
    private RedisTemplate<String, String> redisTemplate;


    public void sendMessage(String channel, Serializable message) {

        redisTemplate.convertAndSend(channel, message);
    }
    @RequestMapping(value = "sendmsg")
    //@RequestMapping(value = "sendmsg",produces = "application/json;charset=utf-8")
    @ResponseBody
    public  String sendMessage(){
       try{
           MessageVo messageVo = new MessageVo();
           messageVo.setDate("20171207");
           messageVo.setKey("tuofuwebservices");
           List<String> webmatids=new ArrayList<String>();
           webmatids.add("123"); webmatids.add("456");
           Map<String,List<String>> webmaidmtaids=new HashMap<String, List<String>>();
           webmaidmtaids.put("1147",webmatids);
           messageVo.setWebsitematids(webmaidmtaids);
           //异步发送短信到redis队列
           sendMessage("msgname", messageVo);//"java",和applicationContext_redis中topic配置一样
           return "发送成功";
       }catch (Exception e){
           e.printStackTrace();
           return "发送失败";
       }

    }
}