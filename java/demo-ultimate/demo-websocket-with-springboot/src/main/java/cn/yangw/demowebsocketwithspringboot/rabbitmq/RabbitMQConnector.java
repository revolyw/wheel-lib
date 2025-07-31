package cn.yangw.demowebsocketwithspringboot.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * TODO comments
 *
 * @author Willow
 * @date 2020/7/29
 */
//@Component
public class RabbitMQConnector {
	public RabbitMQConnector() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		// "guest"/"guest" by default, limited to localhost connections
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setVirtualHost("localhost");
		factory.setHost("rabbitmq.willowspace.cn");
		factory.setPort(5672);
		Connection conn = factory.newConnection();
	}


}
