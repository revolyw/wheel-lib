package com.willow.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO comments
 *
 * @author Willow
 * @date 3/1/19
 */
public class BioServer extends Thread {
	private static AtomicInteger counter = new AtomicInteger(0);
	private ServerSocket serverSocket;

	public int getPort() {
		return serverSocket.getLocalPort();
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(7777);
//			ExecutorService executorService = Executors.newFixedThreadPool(5);
			// synchronous
			while (true) {
				// blocking
				// open a socket stream for every client
				Socket socket = serverSocket.accept();
				RequestHandler requestHandler = new RequestHandler(socket);
				requestHandler.start();
//				executorService.execute(requestHandler);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * client handler
	 */
	static class RequestHandler extends Thread {
		private Socket socket;

		RequestHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			// socket 在这里打开连接，进行 IO 操作
			try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				out.println("i come from a server " + dateFormat.format(new Date()));
				out.flush();
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	static class Client extends Thread {
		private BioServer server;

		public Client(BioServer bioServer) {
			this.server = bioServer;
		}

		@Override
		public void run() {
			request(server);
		}

		public static void request(BioServer server) {
			try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				bufferedReader.lines().map(s -> "[ server response " + counter.incrementAndGet() + " ] " + s).forEach(System.out::println);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		BioServer server = new BioServer();
		server.start();
		Thread.sleep(2000);

		for (int i = 0; i < 1000; i++) {
//			Client.request(server);

			// 并发
			Client client = new Client(server);
			client.start();
		}
	}
}



