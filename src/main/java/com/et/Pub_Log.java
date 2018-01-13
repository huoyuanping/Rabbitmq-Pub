package com.et;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;



/**
 *发布订阅模式
 * @author Administrator
 *
 */
public class Pub_Log {
	
	/**
	 *交换器的名字 交换器有很多类型
	 */
	private static String EXCHANGE_NAME="amp_log";
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
        //发布订阅模式 没有key  为空       类型（fanout）	
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout",true);  
        String message = null;  
        //同时发送5条消息  
        for(int i=0;i<10;i++){  
            message="发送第"+i+"消息";  
            //第二个参数就是routingkey  不填 默认会转发给所有的订阅者队列  
            channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));  
        }  
          
        System.out.println(" [x] Sent 6 message");  
        
	}
}
