package cn.yangw.demowebsocketwithspringboot.heartbeat;

import org.springframework.messaging.Message;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;

/**
 * TODO comments
 *
 * @author Willow
 * @date 2020/2/18
 * @see org.springframework.context.ApplicationListener
 */
public class SessionDisconnectEvent extends AbstractSubProtocolEvent {
	protected SessionDisconnectEvent(Object source, Message<byte[]> message) {
		super(source, message);
	}
}
