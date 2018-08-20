package com.advanced.netIO.NIO.test1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
 
public class NioServer {
    private Selector selector; 
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化 
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化
    SimpleDateFormat format=new SimpleDateFormat("mm:ss:SSS");
    public static void main(String[] args) throws IOException {
        System.out.println("server started...");
        new NioServer().start();
    }
    String str;
    public void start() throws IOException {
        // 打开服务器套接字通道 
        ServerSocketChannel ssc = ServerSocketChannel.open(); 
        // 服务器配置为非阻塞 
        ssc.configureBlocking(false); 
        // 进行服务的绑定 
        ssc.bind(new InetSocketAddress("localhost", 8001)); 
        
        // 通过open()方法找到Selector
        selector = Selector.open(); 
        // 注册到selector，等待连接
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        
        while (!Thread.currentThread().isInterrupted()) { 
            selector.select(); //没有请求连接或默认阻塞
            Set<SelectionKey> keys = selector.selectedKeys(); 
            Iterator<SelectionKey> keyIterator = keys.iterator(); 
            while (keyIterator.hasNext()) { 
                SelectionKey key = keyIterator.next(); 
                if (!key.isValid()) { 
                    continue; 
                } 
                if (key.isAcceptable()) { 
                    accept(key); 
                } else if (key.isReadable()) { 
                    read(key); 
                } else if (key.isWritable()) {
                    write(key);
                }
                keyIterator.remove(); //该事件已经处理，可以丢弃
            } 
        } 
    }

    private void write(SelectionKey key) throws IOException, ClosedChannelException {
        SocketChannel channel = (SocketChannel) key.channel();
        System.out.println(format.format(new Date())+"服务端写数据:"+str);
        
        sendBuffer.clear();
        sendBuffer.put(str.getBytes());
        sendBuffer.flip();
        channel.write(sendBuffer);
        channel.register(selector, SelectionKey.OP_READ);
    } 
 
    private void read(SelectionKey key) throws IOException { 
        SocketChannel socketChannel = (SocketChannel) key.channel(); 
 
        this.readBuffer.clear();
        int numRead;
        try { 
            numRead = socketChannel.read(this.readBuffer); 
        } catch (IOException e) { 
            key.cancel();
            socketChannel.close(); 
            
            return; 
        } 
        
        str = new String(readBuffer.array(), 0, numRead);
        System.out.println(format.format(new Date())+"服务端读数据:"+str);
        socketChannel.register(selector, SelectionKey.OP_WRITE);
    } 
 
    private void accept(SelectionKey key) throws IOException { 
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel(); 
        SocketChannel clientChannel = ssc.accept(); 
        clientChannel.configureBlocking(false); 
        clientChannel.register(selector, SelectionKey.OP_READ); 
        System.out.println(format.format(new Date())+"a new client connected "+clientChannel.getRemoteAddress());
    } 
 

}