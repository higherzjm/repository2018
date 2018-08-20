package com.advanced.websocket.example_20180227;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

public class ExamplesConfig
  implements ServerApplicationConfig
{
  @Override
  public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned)
  {
    Set<ServerEndpointConfig> result = new HashSet();
    if (scanned.contains(EchoEndpoint.class)) {
      result.add(ServerEndpointConfig.Builder.create(EchoEndpoint.class, "/websocket/echoProgrammatic").build());
    }
    return result;
  }
  @Override
  public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned)
  {
    Set<Class<?>> results = new HashSet();
    for (Class<?> clazz : scanned) {
      if (clazz.getPackage().getName().startsWith("websocket.")) {
        results.add(clazz);
      }
    }
    return results;
  }
}