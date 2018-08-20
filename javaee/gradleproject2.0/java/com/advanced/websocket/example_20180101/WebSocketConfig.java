package com.advanced.websocket.example_20180101;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
  
/** 
 * Spring框架WebSocket配置管理类 
 * @author guoyankun 
 */  
@Configuration  
@EnableWebMvc  
@EnableWebSocket  
public class WebSocketConfig extends WebMvcConfigurerAdapter implements  
        WebSocketConfigurer {  
    /** 
     * 注册websocket 
     */  
    @Override  
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {  
        // 注册多个websocket服务，addHandler第一个参数是websocket的具体业务处理类，第二个参数collectionList相当于endpoint  
        registry.addHandler(RealSocketHandler(), "/websocket").addInterceptors(new WebSocketHandshakeInterceptor());
        // 可以注册多个websocket的endpoint  
    }  
    @Bean  
    public WebSocketHandler RealSocketHandler() {  
        return new RealSocketHandler();  
    }  
}  