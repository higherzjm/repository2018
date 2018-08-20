package com.advanced.concurrencyclass.CountDownLatch;

public class Participant implements  Runnable{
    private VideoConference videoConference;  
    private String name;  
  
    public Participant(String name, VideoConference videoConference) {  
        this.name = name;  
        this.videoConference = videoConference;  
    }  
    public void run(){  
        try {  
            //do something  
            Thread.sleep(50);  
            //  
            videoConference.arrive(name);  
        }catch (InterruptedException e){  
            e.printStackTrace();  
        }  
    }  
  
    public static void main(String[] args){  
        VideoConference videoConference = new VideoConference(10);  
        Thread videoThread = new Thread(videoConference);  
        videoThread.start();  
        for(int i=0; i<10; i++){  
            Thread thread = new Thread(new Participant("participant:"+i,videoConference));  
            thread.start();  
        }  
    }  
} 