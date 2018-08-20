package com.advanced.websocket.example_20180228;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@ServerEndpoint("/webSocketServer/{userId}")
public class WebSocketServer {
     
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     * @throws IOException
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) throws IOException {
        System.out.println("Client connected  "+userId);
    }
     
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("userId") String userId, Session session)throws IOException {
        System.out.println("Connection closed");
    }
     
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     * @throws IOException
     */
    @OnMessage
    public void onMessage(@PathParam("userId") String userId, String message, Session session) throws IOException {
        System.out.println("Received: " + message);
        session.getBasicRemote().sendText("This is the first server message");
        int sentMessages = 0;
        while(sentMessages < 3){
          session.getBasicRemote().
            sendText("This is an intermediate server message. Count: "
              + sentMessages);
          sentMessages++;
        }
        session.getBasicRemote().sendText("This is the last server message");
    }
     
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }

    public WebSocketServer() {
        System.out.println("-------------------");
    }
}