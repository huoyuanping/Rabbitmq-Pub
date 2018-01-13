package com.et;

import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * ��������ģʽ
 * @author Administrator
 *
 */
public class Pub_Work {
	
	/**
	 * ���񱻷��͵Ķ�������
	 */
	static String QUEUE_NAME="WORK_QUEUE";
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
    
		//���崴��һ������  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //���͵���Ϣ
        String message="Hello World";
        for(int i=0;i<10;i++){
        	 //�������������ⷢ����Ϣ��pulsh�� ע�ⷢ�ͺͽ��ն���ͬ�ַ������������
            channel.basicPublish("", QUEUE_NAME, null,("����"+i).getBytes("UTF-8"));
        }
        System.out.println(" [x] Sent 5 message"); 

        //�ر�Ƶ��
        channel.close();
        //�ر�����
        connection.close();  
	}
}
