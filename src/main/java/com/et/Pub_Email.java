package com.et;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/**
 *  ��ģʽ
 * @author Administrator
 *
 */
public class Pub_Email {
	/**
	 * ���л�
	 * ���������л�תΪ�ֽ�����
	 */
	public static byte[] ser(Object obj)throws Exception{
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(bos);
		oos.writeObject(obj);
		return bos.toByteArray();
	}
	/**
	 * �����л�
	 * ���ֽ����鷴���л�תΪ�ֽ�����
	 */
	public static Object dser(byte[] src)throws Exception{
		ByteArrayInputStream bis=new ByteArrayInputStream(src);
		ObjectInputStream ois=new ObjectInputStream(bis);
		ois.readObject();
		return ois.readObject();
	}
	/**
	 * ���񱻷��͵Ķ�������
	 */
	static String QUEUE_NAME="MAIL_QUEUE";
	public static void main(String[] args) throws Exception, TimeoutException {
		// ģ��һ��������Ϣ
		Map<String, String> map=new HashMap<String, String>();
		map.put("sendTo","2549882772@qq.com");
		map.put("subject", "�����ʼ�");
		map.put("content", "ע��ɹ�������֤����123456");
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
        //�������������ⷢ����Ϣ��pulsh�� ע�ⷢ�ͺͽ��ն���ͬ�ַ������������
        channel.basicPublish("", QUEUE_NAME, null, ser(map));
        System.out.println(" [x] Sent 5 message"); 
        //�ر�Ƶ��
        channel.close();
        //�ر�����
        connection.close();  
	}
}
