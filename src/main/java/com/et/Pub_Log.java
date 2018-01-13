package com.et;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;



/**
 *��������ģʽ
 * @author Administrator
 *
 */
public class Pub_Log {
	
	/**
	 *������������ �������кܶ�����
	 */
	private static String EXCHANGE_NAME="amp_log";
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
        //��������ģʽ û��key  Ϊ��       ���ͣ�fanout��	
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout",true);  
        String message = null;  
        //ͬʱ����5����Ϣ  
        for(int i=0;i<10;i++){  
            message="���͵�"+i+"��Ϣ";  
            //�ڶ�����������routingkey  ���� Ĭ�ϻ�ת�������еĶ����߶���  
            channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));  
        }  
          
        System.out.println(" [x] Sent 6 message");  
        
	}
}
