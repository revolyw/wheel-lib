package cn.yangw.demowebsocketwithspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * 消息代理
 *
 * @author Willow
 * @date 2020/2/18
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompOverWebSocketConfig implements WebSocketMessageBrokerConfigurer {
	private final WebSocketDecoratorFactory webSocketDecoratorFactory;

	public StompOverWebSocketConfig(WebSocketDecoratorFactory webSocketDecoratorFactory) {
		this.webSocketDecoratorFactory = webSocketDecoratorFactory;
	}

	/**
	 * 消息传输参数配置
	 *
	 * @param registration
	 */
	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
		/**
		 * setSendTimeLimit 超时时间 单位毫秒 默认值 10 * 1000 = 10s
		 * setSendBufferSizeLimit 缓存空间 单位字节 默认值 512 * 1024 = 512K
		 * setMessageSizeLimit 消息大小 单位字节 默认值 64 * 1024 = 64K
		 */
		registration.setSendTimeLimit(10 * 1000)
				.setSendBufferSizeLimit(512 * 1024)
				.setMessageSizeLimit(64 * 1024)
				.addDecoratorFactory(webSocketDecoratorFactory);
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//消息上行前缀
		registry.setApplicationDestinationPrefixes("/ws");
		//消息下行前缀
		registry.enableStompBrokerRelay("/topic")
				.setRelayHost("rabbitmq.willowspace.cn")
				.setRelayPort(61613);
//				.setClientLogin("admin")
//				.setClientPasscode("admin");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//websocket 连接点。 paths 为连接路由
		registry.addEndpoint("/demo-websocket-with-springboot").withSockJS();
	}

	/**
	 * @param registration
	 */
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptorAdapter() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
				if (null == accessor) {
					throw new RuntimeException("StompHeaderAccessor is null");
				}
				//1. 判断是否首次连接请求
				if (StompCommand.CONNECT.equals(accessor.getCommand())) {
					System.out.println(accessor.getCommand());
//					//2. 验证是否登录
//					String username = accessor.getNativeHeader("username").get(0);
//					String password = accessor.getNativeHeader("password").get(0);
//					for (Map.Entry<String, String> entry : Users.USERS_MAP.entrySet()) {
////                        System.out.println(entry.getKey() + "---" + entry.getValue());
//						if (entry.getKey().equals(username) && entry.getValue().equals(password)) {
//							//验证成功,登录
//							Authentication user = new Authentication(username); // access authentication header(s)}
//							accessor.setUser(user);
//							return message;
//						}
//					}
					return message;
				} else if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
					System.out.println(accessor.getCommand());
				} else if (StompCommand.UNSUBSCRIBE.equals(accessor.getCommand())) {
					System.out.println(accessor.getCommand());
				} else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
					System.out.println(accessor.getCommand());
				} else {
					System.out.println(accessor.getCommand());
				}
				return message;
			}
		});
	}
}
