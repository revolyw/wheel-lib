package cn.yangw.demowebsocketwithspringboot.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import java.util.Objects;

/**
 * TODO comments
 *
 * @author Willow
 * @date 2020/2/29
 */
@Slf4j
@Component
@Setter
public class WebSocketDecoratorFactory implements WebSocketHandlerDecoratorFactory {
	@Override
	public WebSocketHandler decorate(WebSocketHandler handler) {
		return new WebSocketHandlerDecorator(handler) {

			@Override
			public void afterConnectionEstablished(WebSocketSession session) throws Exception {
				log.info("principal is null afterConnectionEstablished");
				super.afterConnectionEstablished(session);
			}

			@Override
			public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
				if (!Objects.equals(CloseStatus.NORMAL, closeStatus)) {
					log.warn("abnormal {}", closeStatus);
				}
				super.afterConnectionClosed(session, closeStatus);
			}

			@Override
			public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
				log.error("", exception);
				super.handleTransportError(session, exception);
			}
		};
	}
}
