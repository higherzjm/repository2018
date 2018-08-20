package com.j2ee.socket.example1_single;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

public static void main(String[] args) {

        try {
            /** 创建Socket*/
            // 创建一个流套接字并将其连接到指定 IP 地址的指定端口号(本处是本机)
            Socket socket =new Socket("127.0.0.1",2013);
            // 60s超时
            socket.setSoTimeout(60000);
            /** 发送客户端准备传输的信息 */
            // 由Socket对象得到输出流，并构造PrintWriter对象
            PrintWriter printWriter =new PrintWriter(socket.getOutputStream(),true);
            // 将输入读入的字符串输出到Server
            //BufferedReader sysBuff =new BufferedReader(new InputStreamReader(System.in));
            printWriter.println("输入的内容");
            // 刷新输出流，使Server马上收到该字符串
            printWriter.flush();
            /** 用于获取服务端传输来的信息 */
            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
           BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 输入读入一字符串
            String result = bufferedReader.readLine();
            System.out.println("Server say : " + result);
            /** 关闭Socket*/
            printWriter.close();
            bufferedReader.close();
            socket.close();
        }catch (Exception e) {

            System.out.println("Exception:" + e);

        }

    }

}