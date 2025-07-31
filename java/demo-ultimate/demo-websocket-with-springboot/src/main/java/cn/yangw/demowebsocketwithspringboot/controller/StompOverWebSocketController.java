package cn.yangw.demowebsocketwithspringboot.controller;

import cn.yangw.demowebsocketwithspringboot.Greeting;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * websocket 接口
 * 路由接口开发
 *
 * @author Willow
 * @date 2020/2/18
 */
@Controller
public class StompOverWebSocketController {
	private final SimpMessagingTemplate simpMessagingTemplate;

	public StompOverWebSocketController(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	/**
	 * STOMP 推送
	 */
	@MessageMapping("/hello")
	public void getAndSend(SimpMessageHeaderAccessor headerAccessor, Greeting greeting) throws Exception {
		simpMessagingTemplate.convertAndSend("/topic/" + greeting.getRoomId(), greeting);
	}


}
