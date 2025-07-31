package com.willow.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

/**
 * TODO comments
 *
 * @author Willow
 * @date 3/1/19
 */
public class NioServer extends Thread {
	private static final int PORT = 8888;

	public int getPort() {
		return PORT;
	}

	@Override
	public void run() {
		try (Selector selector = Selector.open(); ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			serverSocketChannel
					.bind(new InetSocketAddress(InetAddress.getLocalHost(), PORT))
					.configureBlocking(false);
			// register into Selector，attention to the accept operation
			// IllegalBlockingModeException if configureBlocking is true
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			while (true) {
				// blocking for waiting ready Channel，this is a key point for reason why efficient of nio
				selector.select();
				// events
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectedKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					//remove events
					iterator.remove();
					if (!selectionKey.isValid()) {
						continue;
					}
					if (selectionKey.isAcceptable()) {
						try (SocketChannel client = ((ServerSocketChannel) selectionKey.channel()).accept()) {
							client.configureBlocking(false);
							DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
							ByteBuffer byteBuffer = Charset.defaultCharset().encode("i come from a server " + dateFormat.format(new Date()));
							client.write(byteBuffer);
							Thread.sleep(5000);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (selectionKey.isReadable()) {
						//do something
					} else if (selectionKey.isWritable()) {
						//do something
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class Client extends Thread {
		private NioServer server;

		public Client(NioServer server) {
			this.server = server;
		}

		@Override
		public void run() {
			request(server);
		}

		public static void request(NioServer server) {
			try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				bufferedReader.lines().map(s -> "[ server response ] " + s).forEach(System.out::println);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		NioServer server = new NioServer();
		server.start();
		Thread.sleep(2000);

		for (int i = 0; i < 100; i++) {
//			Client.request(server);

			// 并发
			Client client = new Client(server);
			client.start();
		}
	}
}


