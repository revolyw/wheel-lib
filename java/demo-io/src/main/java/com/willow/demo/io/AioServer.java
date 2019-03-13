package com.willow.demo.io;

import org.omg.PortableServer.THREAD_POLICY_ID;
import sun.tools.tree.FinallyStatement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * TODO comments
 *
 * @author Willow
 * @date 3/1/19
 */
public class AioServer extends Thread {
	private static final int PORT = 9999;

	public int getPort() {
		return PORT;
	}

	@Override
	public void run() {
		try (AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open()) {
			asynchronousServerSocketChannel.bind(new InetSocketAddress(PORT));
			asynchronousServerSocketChannel.accept(asynchronousServerSocketChannel, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
				// 为异步操作指定 CompletionHandler 回调函数
				@Override
				public void completed(AsynchronousSocketChannel socketChannel, AsynchronousServerSocketChannel serverSocketChannel) {
					if (socketChannel.isOpen()) {
						serverSocketChannel.accept(serverSocketChannel, this);
						// 另外一个 write（sock，CompletionHandler{}）
						DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
						socketChannel.write(Charset.defaultCharset().encode("i come from a server "+dateFormat.format(new Date())));
						try {
							System.out.println(Thread.currentThread().getName());
							Thread.sleep(5000);
							socketChannel.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				@Override
				public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
					exc.printStackTrace();
				}
			});
			while (true) {
				Thread.sleep(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class Client extends Thread {
		private AioServer server;

		public Client(AioServer server) {
			this.server = server;
		}

		@Override
		public void run() {
			request(server);
		}

		public static void request(AioServer server) {
			try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				bufferedReader.lines().map(s -> "[ server response ] " + s).forEach(System.out::println);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		AioServer server = new AioServer();
		server.start();
		Thread.sleep(2000);

		for (int i = 0; i < 10; i++) {
//			Client.request(server);

			// 并发
			Client client = new Client(server);
			client.start();
		}
	}
}
