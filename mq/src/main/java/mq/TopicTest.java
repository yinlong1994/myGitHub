package mq;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class TopicTest {

	//发布者
	@Test
	public void testProducer() throws JMSException {
		
//		第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.38.11:61616");
		
//		第二步：使用ConnectionFactory对象创建一个Connection对象。
		Connection connection = connectionFactory.createConnection();
		
//		第三步：开启连接，调用Connection对象的start方法。
		connection.start();
		
//		第四步：使用Connection对象创建一个Session对象。
		//参数1：是否开启事务：true，则第二个参数忽略。
		//参数2：消息的应答模式：1、自定应答；2、手动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
//		第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个topic对象。
		Topic topic = session.createTopic("test-topic");
		
//		第六步：使用Session对象创建一个Producer对象。
		MessageProducer producer = session.createProducer(topic);
		
//		第七步：创建一个Message对象，创建一个TextMessage对象。
		TextMessage textMessage = session.createTextMessage("hello 消息的发布者");		
		
//		第八步：使用Producer对象发送消息。
		producer.send(textMessage);
		
//		第九步：关闭资源。
		producer.close();
		session.close();
		connection.close();
	}
	
	
	//订阅者
	@Test
	public void testSubscriber() throws JMSException, IOException {
		
		System.out.println("topic的消费端3....启动...");
		
//		第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.38.11:61616");
		
//		第二步：使用ConnectionFactory对象创建一个Connection对象。
		Connection connection = connectionFactory.createConnection();
		
//		第三步：开启连接，调用Connection对象的start方法。
		connection.start();
		
//		第四步：使用Connection对象创建一个Session对象。
		//参数1：是否开启事务：true，则第二个参数忽略。
		//参数2：消息的应答模式：1、自定应答；2、手动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
//		第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个topic对象。
		Topic topic = session.createTopic("test-topic");
		
//		第六步：使用Session对象创建一个Consumer对象。
		MessageConsumer consumer = session.createConsumer(topic);		
		
//		第七步：接收消息。
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				
				TextMessage textMessage = (TextMessage)message;
				try {
					String text = textMessage.getText();
//					第八步：打印消息。
					System.out.println(text);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		System.in.read();

//		第九步：关闭资源。
		consumer.close();
		session.close();
		connection.close();
	}
}
