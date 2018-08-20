package com.advanced.websocket.example_20180227;

import javax.websocket.*;
import java.io.IOException;
import java.nio.ByteBuffer;

public class EchoEndpoint
  extends Endpoint
{
  public void onOpen(Session session, EndpointConfig endpointConfig)
  {
    RemoteEndpoint.Basic remoteEndpointBasic = session.getBasicRemote();
    session.addMessageHandler(new EchoMessageHandlerText(remoteEndpointBasic, null));
    session.addMessageHandler(new EchoMessageHandlerBinary(remoteEndpointBasic, null));
  }
  
  private static class EchoMessageHandlerText
    implements MessageHandler.Partial<String>
  {
    private final RemoteEndpoint.Basic remoteEndpointBasic;
    
    private EchoMessageHandlerText(RemoteEndpoint.Basic endpointBasic, RemoteEndpoint.Basic remoteEndpointBasic)
    {
      this.remoteEndpointBasic = remoteEndpointBasic;
    }
    
    public void onMessage(String message, boolean last)
    {
      try
      {
        if (this.remoteEndpointBasic != null) {
          this.remoteEndpointBasic.sendText(message, last);
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  private static class EchoMessageHandlerBinary
    implements MessageHandler.Partial<ByteBuffer>
  {
    private final RemoteEndpoint.Basic remoteEndpointBasic;
    
    private EchoMessageHandlerBinary(RemoteEndpoint.Basic endpointBasic, RemoteEndpoint.Basic remoteEndpointBasic)
    {
      this.remoteEndpointBasic = remoteEndpointBasic;
    }
    
    public void onMessage(ByteBuffer message, boolean last)
    {
      try
      {
        if (this.remoteEndpointBasic != null) {
          this.remoteEndpointBasic.sendBinary(message, last);
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
