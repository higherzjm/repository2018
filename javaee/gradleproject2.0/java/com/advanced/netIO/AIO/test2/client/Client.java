package com.advanced.netIO.AIO.test2.client;

import java.util.Scanner;
public class Client {
    private static String DEFAULT_HOST = "127.0.0.1";
	private static int DEFAULT_PORT = 12345;
	private static AsyncClientHandler clientHandle;
	public static void start(){
		start(DEFAULT_HOST,DEFAULT_PORT);
	}
	public static synchronized void start(String ip,int port){
		if(clientHandle!=null)
			return;
		clientHandle = new AsyncClientHandler(ip,port);
		new Thread(clientHandle,"Client").start();
	}
	//向服务器发送消息
	public static boolean sendMsg(String msg) throws Exception{
		if(msg.equals("q")) return false;
		clientHandle.sendMsg(msg);
		return true;
	}
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{
		Client.start();
		System.out.println("请输入请求消息：");
		Scanner scanner = new Scanner(System.in);
		while(Client.sendMsg(scanner.nextLine()));
		/*for (int i=0;i<10;i++){
			ClientThred clientThred=new ClientThred("127.0.0.1",12345);
			new Thread(clientThred).start();
		}*/


	}
}

class  ClientThred implements  Runnable{

	public  String host="";
	public Integer port=0;

	public ClientThred(String host, Integer ip) {
		this.host = host;
		this.port = ip;
	}
	public   AsyncClientHandler clientHandle;
	public  void start(){
		start(host,port);
	}

	public  synchronized void start(String ip,int port){
		if(clientHandle!=null)
			return;
		clientHandle = new AsyncClientHandler(ip,port);
		new Thread(clientHandle,"Client").start();
	}
	//向服务器发送消息
	public  boolean sendMsg(String msg) throws Exception{
		if(msg.equals("q")) return false;
		clientHandle.sendMsg(msg);
		return true;
	}
	@Override
	public void run() {
		start();
		System.out.println("请输入请求消息：");
		Scanner scanner = new Scanner(System.in);
		try {
			while(sendMsg(scanner.nextLine()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}