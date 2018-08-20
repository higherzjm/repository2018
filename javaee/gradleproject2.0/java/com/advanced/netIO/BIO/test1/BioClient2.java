package com.advanced.netIO.BIO.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class BioClient2 {
    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Socket socket = null;
                    BufferedReader in = null;
                    PrintWriter out = null;
                    try {
                        socket = new Socket("127.0.0.1", 1986);
                        System.out.println(" client init 2 ");
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out = new PrintWriter(socket.getOutputStream(),true);
                        out.print("客户端发送\n");
                        out.flush();
                        System.out.println(" client send ");
                        System.out.println(" client received : " + in.readLine());

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }


    }
}