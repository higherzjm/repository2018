package com.advanced.concurrencyclass.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class VideoConference implements  Runnable{
    private final CountDownLatch countDownLatch;
    private int number;  
    public VideoConference(int number) {  
        this.number = number;  
        this.countDownLatch = new CountDownLatch(number);//使用Number初始化其内部的计数器，当初始化完成后，不能再次初始化  
    }  
    public void arrive(String name){  
        //每个需要同步的任务，在任务完成时，需要调用该方法  
        countDownLatch.countDown();//countDownLatch内部的计数器减1  
        System.out.print("arrive:"+name+"\n");  
        try{  
            countDownLatch.await();//await方法是线程进入休眠，当countDownLatch计数器为0时，将被唤醒  
            //线程被唤醒，在这里可以执行一系列任务  
            System.out.print("name:"+name + " say:let's start..." +"\n");  
        }catch (InterruptedException e){  
            e.printStackTrace();  
        }  
    }  
    public void run(){  
        System.out.print("has arrive:"+(number-countDownLatch.getCount())+"\n");  
        try{  
            countDownLatch.await();//await方法是线程进入休眠，当countDownLatch计数器为0时，将被唤醒  
            //线程被唤醒，在这里可以执行一系列任务  
            System.out.print("all arrived:"+(number-countDownLatch.getCount())+"\n");  
        }catch (InterruptedException e){  
            e.printStackTrace();  
        }  
    }  
}  