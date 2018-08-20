package com.advanced.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
//生产者/消费者
public class AppProducer {
    public static final String url = "tcp://localhost:61616";
    public static final String queueName = "queue-test";
    public static final String topicName = "topic-test";

    public static void main(String[] args) {
        try {
            //AppProducer.class.newInstance().producerQueue();
            AppProducer.class.newInstance().producerTopic();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 队列模式的生产者AppProducer
     * @throws JMSException
     */
    @Test
    public void producerQueue() throws JMSException {
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

        //6创建一个生产者
        MessageProducer producer = session.createProducer(destination);
        for (int i = 100; i < 10000; i++) {
            //7.创基建消息
            TextMessage textMessage = session.createTextMessage("test:"+i);
            producer.send(textMessage);
            System.out.println("发送消息:"+textMessage.getText());
        }
        //9关闭连接
        connection.close();
    }

    /**
     * 主题模式发布者AppProducer
     * @throws JMSException
     */
    @Test
    public void producerTopic() throws JMSException {
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

        //6创建一个生产者
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 100; i++) {
            //7.创基建消息
            TextMessage textMessage = session.createTextMessage("test:"+i);
            producer.send(textMessage);
            System.out.println("发送消息:"+textMessage.getText());
        }
        //9关闭连接
        connection.close();
    }



}