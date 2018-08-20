package com.javase.thread.daemonthread_userthread;

/**
 * Created by zjm on 2018/4/3.
 * 守护线程与普通线程
 */
public class Test_20180403 {
    public static void main(String[] args) {

        test_userThread();//普通线程:所有结束才会结束
        //test_daemonThread();// 守护线程:主进程结束
    }
    public static void test_userThread(){
        stopThread  st=new stopThread ();
        Thread t=new Thread(st);
        t.start();
        for(int i=0;i<20;i++)
        {
            st.change(i);

        }
        System.out.println("主线程结束.....");
    }

    /**
     * 主进程结束,线程也结束
     */
    public static void test_daemonThread(){
        stopThread  st=new stopThread ();
        Thread t1=new Thread(st);
        t1.setDaemon(true);
        t1.start();
        for(int i=0;i<20;i++)
        {
            st.change(i);

        }
        System.out.println("主线程结束.....");
    }
}

class stopThread implements Runnable{
    public  void run() {
       for (int i=0;i<10000;i++){
           System.out.println("run i:"+i);
       }
    }
    public void change(int i)
    {
        System.out.println("i="+i);
    }

}

