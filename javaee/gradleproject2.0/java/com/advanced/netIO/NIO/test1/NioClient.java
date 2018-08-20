package com.advanced.netIO.NIO.test1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set; 
 
 
public class NioClient {
 
    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    SimpleDateFormat format=new SimpleDateFormat("mm:ss:SSS");
    public void start() throws IOException { 
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("localhost", 8001));
        Selector selector = Selector.open();
        sc.register(selector, SelectionKey.OP_CONNECT);
        
        Scanner scanner = new Scanner(System.in); 
        while (true) { 
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            System.out.println("keys=" + keys.size()); 
            Iterator<SelectionKey> keyIterator = keys.iterator(); 
            while (keyIterator.hasNext()) { 
                SelectionKey key = keyIterator.next(); 
                keyIterator.remove(); 
                if (key.isConnectable()) {
                    sc.finishConnect(); 
                    sc.register(selector, SelectionKey.OP_WRITE); 
                    System.out.println(format.format(new Date())+"server connected...");
                    break; 
                } else if (key.isWritable()) { //写数据 
                    System.out.print(format.format(new Date())+"please input message:");
                    String message = scanner.nextLine(); 
                    writeBuffer.clear();
                    writeBuffer.put(message.getBytes());
                    writeBuffer.flip();
                    sc.write(writeBuffer); 
                    sc.register(selector, SelectionKey.OP_READ);
                    
                } else if (key.isReadable()){//读取数据
                    System.out.print(format.format(new Date())+"receive message:");
                    SocketChannel client = (SocketChannel) key.channel();
                    readBuffer.clear();
                    int num = client.read(readBuffer);
                    System.out.println(format.format(new Date())+"客户端读数据:"+new String(readBuffer.array(),0, num));
                    sc.register(selector, SelectionKey.OP_WRITE);
                }
            } 
        } 
    } 
 
    public static void main(String[] args) throws IOException {
        new NioClient().start();
    }
}
