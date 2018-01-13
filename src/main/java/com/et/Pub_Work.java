package com.et;

import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 工作队列模式
 * @author Administrator
 *
 */
public class Pub_Work {
	
	/**
	 * 任务被发送的队列名称
	 */
	static String QUEUE_NAME="WORK_QUEUE";
	public static void main(String[] args) throws Exception, TimeoutException {

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
        for(int i=0;i<10;i++){
        	 //生产者向消费这发送消息（pulsh） 注意发送和接收段相同字符集则出现乱码
            channel.basicPublish("", QUEUE_NAME, null,("这是"+i).getBytes("UTF-8"));
        }
        System.out.println(" [x] Sent 5 message"); 

        //关闭频道
        channel.close();
        //关闭连接
        connection.close();  
	}
}
