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
 *  简单模式
 * @author Administrator
 *
 */
public class Pub_Email {
	/**
	 * 序列化
	 * 将对象序列化转为字节数组
	 */
	public static byte[] ser(Object obj)throws Exception{
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(bos);
		oos.writeObject(obj);
		return bos.toByteArray();
	}
	/**
	 * 反序列化
	 * 将字节数组反序列化转为字节数组
	 */
	public static Object dser(byte[] src)throws Exception{
		ByteArrayInputStream bis=new ByteArrayInputStream(src);
		ObjectInputStream ois=new ObjectInputStream(bis);
		ois.readObject();
		return ois.readObject();
	}
	/**
	 * 任务被发送的队列名称
	 */
	static String QUEUE_NAME="MAIL_QUEUE";
	public static void main(String[] args) throws Exception, TimeoutException {
		// 模拟一个任务消息
		Map<String, String> map=new HashMap<String, String>();
		map.put("sendTo","2549882772@qq.com");
		map.put("subject", "测试邮件");
		map.put("content", "注册成功您的验证马是123456");
		//推送到队列服务器
		//连接远程rabbit-server服务器  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.73.130");  
        factory.setPort(5672);  
        //通过ConnectionFactory创建一个连接connection
        Connection connection = factory.newConnection();  
        //通过connection创建一个频道channel
        Channel channel = connection.createChannel();  
    
		//定义创建一个队列  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //发送的消息
        String message="Hello World";
        //生产者向消费这发送消息（pulsh） 注意发送和接收段相同字符集则出现乱码
        channel.basicPublish("", QUEUE_NAME, null, ser(map));
        System.out.println(" [x] Sent 5 message"); 
        //关闭频道
        channel.close();
        //关闭连接
        connection.close();  
	}
}
