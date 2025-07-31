package cn.yangw.demowebsocketwithspringboot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO comments
 *
 * @author Willow
 * @date 2020/2/18
 */
@AllArgsConstructor
@Data
public class Greeting implements Serializable {
	String roomId;
	String user;
	String message;
}
