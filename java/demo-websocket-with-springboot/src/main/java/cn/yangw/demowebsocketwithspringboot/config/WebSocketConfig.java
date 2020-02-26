package cn.yangw.demowebsocketwithspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * websocket 服务配置
 *
 * @author Willow
 * @date 2020/2/18
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Bean
	public ServletServerContainerFactoryBean createWebSocketContainer() {
		ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
		container.setMaxTextMessageBufferSize(8192);
		container.setMaxBinaryMessageBufferSize(8192);
//		container.setMaxSessionIdleTimeout(5000L);
		return container;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
		WebSocketHandler webSocketHandler = customHandler();
		HandshakeInterceptor handshakeInterceptor = customHttpSessionHandshakeInterceptor();
		webSocketHandlerRegistry.addHandler(webSocketHandler, "/ws")
				.addInterceptors(handshakeInterceptor);

//				.setAllowedOrigins("http://mydomain.com")
//				.withSockJS();
//				.setTaskScheduler(heartBeatScheduler());
	}

//	@Bean
//	public TaskScheduler heartBeatScheduler() {
//		return new Heart;
//	}

	@Bean
	public HandshakeInterceptor customHttpSessionHandshakeInterceptor() {
		return new CustomHttpSessionHandshakeInterceptor();
	}

	@Bean
	public WebSocketHandler customHandler() {
		return new CustomHandler();
	}
}
