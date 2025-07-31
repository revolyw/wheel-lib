package cn.yangw.demowebsocketwithspringboot.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理收集、分发消息的逻辑
 *
 * @author Willow
 * @date 2020/2/18
 */
@Component
public class CustomHandler extends TextWebSocketHandler {
	Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>(2000);

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getUri() + session.getId() + " has bean connected");
		sessionMap.put(session.getId(), session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("afterConnectionClosed");
	}

	/**
	 * 处理后推送消息下行
	 *
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		TextMessage textMessage = new TextMessage(message.getPayload());
		session.sendMessage(textMessage);
	}
}
