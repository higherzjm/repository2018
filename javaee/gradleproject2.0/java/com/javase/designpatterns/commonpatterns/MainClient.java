package com.javase.designpatterns.commonpatterns;

/**
 * 命令模式
 */
public class MainClient {
  
    public static void main(String[] args) {  
        Receiver receiver = new Receiver();  
        Command command = new ConcreteCommand(receiver);  
        Invoker invoker = new Invoker(command);  
        invoker.action();  
    }  
}  