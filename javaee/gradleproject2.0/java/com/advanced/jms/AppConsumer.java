package com.advanced.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class AppConsumer {
    public static final String url = "tcp://localhost:61616";
    public static final String queueName = "queue-test";
    public static final String topicName = "topic-test";

    public static void main(String[] args) throws JMSException {
        try {
            AppConsumer.class.newInstance().test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void  test(){
        ConsumerQueueThread consumerQueueThread=new ConsumerQueueThread();
        ConsumerTopicThread consumerTopicThread=new ConsumerTopicThread();
        for (int i=0;i<2;i++){
            //Thread thread=new Thread(consumerQueueThread);
            Thread thread=new Thread(consumerTopicThread);
            thread.start();
        }

    }
    //当启动2个消费者，再启动生产者，结果是2个消费者平均消费
    class ConsumerQueueThread implements Runnable{

        @Override
        public void run() {
            try {
                consumerQueue();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        /**
         * 队列模式的消费者AppConsumer
         * @throws JMSException
         */
        @Test
        public void consumerQueue() throws JMSException {
            //1.创建连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

            //2.创建Connection
            Connection connection = connectionFactory.createConnection();

            //3.启动连接
            connection.start();

            //4.创建会话 第一个参数：是否在事务中去处理， 第二个参数.应答模式
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //5.创建一个目标
            Destination destination = session.createQueue(queueName);

            //6创建一个消费者
            MessageConsumer consumber = session.createConsumer(destination);

            //7创建一个监听器
            consumber.setMessageListener(new MessageListener() {

                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println(Thread.currentThread().getName()+":consumerQueue 接收消息："+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                }
            });

            //8关闭连接  消息是异步的 ，在程序退出是关闭，在这里不可以关闭
            //connection.close();
        }
    }

    //启动两个订阅者，再启动发布者，两个订阅者均可收到发布者的消息
    class ConsumerTopicThread implements Runnable{

        @Override
        public void run() {
            try {
                consumerTopic();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        /**
         * 主题模式的消费者AppConsumer
         * @throws JMSException
         */
        @Test
        public void consumerTopic() throws JMSException {
            //1.创建连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

            //2.创建Connection
            Connection connection = connectionFactory.createConnection();

            //3.启动连接
            connection.start();

            //4.创建会话 第一个参数：是否在事务中去处理， 第二个参数.应答模式
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //5.创建一个目标
            Destination destination = session.createTopic(topicName);

            //6创建一个消费者
            MessageConsumer consumber = session.createConsumer(destination);

            //7创建一个监听器
            consumber.setMessageListener(new MessageListener() {

                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println(Thread.currentThread().getName()+":consumerTopic 接收消息："+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                }
            });

            //8关闭连接  消息是异步的 ，在程序退出是关闭
            //connection.close();
        }
    }



}