package com.j2ee.socket.example2_moreclients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends ServerSocket {

    private static final int SERVER_PORT = 2013;

    public Server() throws IOException {
        super(SERVER_PORT);
        try {
            while (true) {
                Socket socket = accept();
                new CreateServerThread(socket);//当有请求时，启一个线程处理
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    //线程类
    class CreateServerThread extends Thread {
        private Socket client;
        private BufferedReader bufferedReader;
        private PrintWriter printWriter;

        public CreateServerThread(Socket s) throws IOException {
            client = s;
            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            printWriter = new PrintWriter(client.getOutputStream(), true);
            start();
        }

        public void run() {
            try {
                String line = bufferedReader.readLine();
                while (!line.equals("bye")) {
                    printWriter.println("1continue, Client(" + getName() + ")!");
                    line = bufferedReader.readLine();
                    System.out.println("1Client(" + getName() + ") say: " + line);
                }
                printWriter.println("bye, Client(" + getName() + ")!");
                printWriter.close();
                bufferedReader.close();
                client.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    public static void main(String[] args) throws IOException {
        new Server();
    }

}