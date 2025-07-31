package com.willow.demo.io;

import java.io.IOException;
import java.nio.file.*;

/**
 * 监听文件变化
 *
 * @author Willow
 * @date 3/10/19
 */
public class FileWatchService {
	public static void main(String[] args) throws IOException, InterruptedException {
		WatchService service = FileSystems.getDefault().newWatchService();
		Paths.get("/var/cache/biomart").register(service,
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY,
				StandardWatchEventKinds.OVERFLOW);
		while (true) {
			WatchKey key = service.take();
			for (WatchEvent event : key.pollEvents()) {
				WatchKey poll = service.poll();
				System.out.println(event.context() + " 文件发生了 " + event.kind() + " 事件!");
			}
			if (!key.reset() || !key.isValid()) {
				break;
			}
		}
	}
}
