package com.advanced.websocket.example_20180227;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/websocket/echoAnnotation")
public class EchoAnnotation
{
  @OnMessage
  public void echoTextMessage(Session session)
  {
    try
    {
      if (session.isOpen()) {
        session.getBasicRemote().sendText(session.getQueryString(), true);
      }
    }
    catch (IOException e)
    {
      try
      {
        session.close();
      }
      catch (IOException e1) {}
    }
  }
  

}
