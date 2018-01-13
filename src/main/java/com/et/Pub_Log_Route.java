package com.et;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;



/**
 *路由模式
 * @author Administrator
 *
 */
public class Pub_Log_Route {
	
	/**
	 *交换器的名字 交换器有很多类型
	 */
	private static String EXCHANGE_NAME="amp_log_route";
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
        //路由模式       类型（direct）	
        channel.exchangeDeclare(EXCHANGE_NAME, "direct",true);  
        String message = null;  
        //第二个参数就是routingkey  不填 默认会转发给所有的订阅者队列  
        channel.basicPublish(EXCHANGE_NAME, "error", MessageProperties.PERSISTENT_TEXT_PLAIN, "系统奔溃，内存溢出".getBytes("UTF-8"));
        channel.basicPublish(EXCHANGE_NAME, "info", MessageProperties.PERSISTENT_TEXT_PLAIN, "张三于2018-1-11号访问该页面".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "info", MessageProperties.PERSISTENT_TEXT_PLAIN, "李四于2018-1-11号登录该网页".getBytes("UTF-8"));  

        System.out.println(" [x] Sent 6 message");  
        
	}
}
