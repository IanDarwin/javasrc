package java7nio;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/** Demonstrate the JavaSE 7+ NIO WatchService */
public class FileWatchServiceDemo {
	public static void main(String[] args) throws Throwable {
		String tempDirPath = "/tmp";
		System.out.println("Starting watcher for " + tempDirPath);
		Path p = Paths.get(tempDirPath);
		WatchService watcher = FileSystems.getDefault().newWatchService();
		Kind<?>[] watchKinds = {ENTRY_CREATE, ENTRY_MODIFY };
		p.register(watcher, watchKinds);
		boolean done = false;
		while (!done) {
			WatchKey key = watcher.take();
			for (WatchEvent<?> e : key.pollEvents()) {
				System.out.println(
					"Saw event " + e.kind() + " on " + 
					e.context());
				if (e.context().toString().equals("MyFileSema.for")) {
					System.out.println("Semaphore found, shutting down watcher");
					done = true;
				}
			}
			if (!key.reset()) {
				System.err.println("Key failed to reset!");
			}
		}
	}
}
