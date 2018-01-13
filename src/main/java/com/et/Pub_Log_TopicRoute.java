package com.et;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;



/**
 *topicģʽ  �����·��ģʽ
 * @author Administrator
 *
 */
public class Pub_Log_TopicRoute {
	
	/**
	 *������������ �������кܶ�����
	 */
	private static String EXCHANGE_NAME="amp_log_topic";
	public static void main(String[] args) throws Exception, TimeoutException {

		//���͵����з�����
		//����Զ��rabbit-server������  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.73.130");  
        factory.setPort(5672);  
        //ͨ��ConnectionFactory����һ������connection
        Connection connection = factory.newConnection();  
        //ͨ��connection����һ��Ƶ��channel
        Channel channel = connection.createChannel();  
        //topicģʽ       ���ͣ�topic�� ϵͳ��.error	
        channel.exchangeDeclare(EXCHANGE_NAME, "topic",true);  
        String message = null;  
        //�ڶ�����������routingkey  ���� Ĭ�ϻ�ת�������еĶ����߶���  
        //���aϵͳ������ϵͳ��c.c1.info��ʾ
        channel.basicPublish(EXCHANGE_NAME, "a.error", MessageProperties.PERSISTENT_TEXT_PLAIN, "ϵͳ�������ڴ����".getBytes("UTF-8"));
        channel.basicPublish(EXCHANGE_NAME, "a.info", MessageProperties.PERSISTENT_TEXT_PLAIN, "������2018-1-11�ŷ��ʸ�ҳ��".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "a.info", MessageProperties.PERSISTENT_TEXT_PLAIN, "������2018-1-11�ŵ�¼����ҳ".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "b.error", MessageProperties.PERSISTENT_TEXT_PLAIN, "ϵͳ�������ڴ����".getBytes("UTF-8"));
        channel.basicPublish(EXCHANGE_NAME, "b.info", MessageProperties.PERSISTENT_TEXT_PLAIN, "������2018-1-11�ŷ��ʸ�ҳ��".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "b.info", MessageProperties.PERSISTENT_TEXT_PLAIN, "������2018-1-11�ŵ�¼����ҳ".getBytes("UTF-8")); 
        channel.basicPublish(EXCHANGE_NAME, "c.c1.info", MessageProperties.PERSISTENT_TEXT_PLAIN, "������2018-1-11�ŵ�¼����ҳ".getBytes("UTF-8"));  

        System.out.println(" [x] Sent 6 message");  
        
	}
}
