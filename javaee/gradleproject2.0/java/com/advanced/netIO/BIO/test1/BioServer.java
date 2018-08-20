package com.advanced.netIO.BIO.test1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class BioServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(1986);
            System.out.println(" server init " );
            Socket socket = null;
            while (true){
                socket = server.accept();
                Thread thread = new BioServerHandle(socket);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}