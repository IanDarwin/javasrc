package nio;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Demonstrate the JavaSE NIO WatchService.
 */
// tag::main[]
public class FileWatchServiceDemo {

	final static String TEMP_DIR_PATH = "/tmp";
	static final String FILE_SEMA_FOR = "MyFileSema.for";
	final static Path SEMAPHORE_PATH = Path.of(TEMP_DIR_PATH ,FILE_SEMA_FOR);
	static volatile boolean done = false;
	final static ExecutorService threadPool = Executors.newSingleThreadExecutor();

	public static void main(String[] args) throws Throwable {
		String tempDirPath = "/tmp";
		System.out.println("Starting watcher for " + tempDirPath);
		System.out.println("Semaphore file is " + SEMAPHORE_PATH);
		Path p = Paths.get(tempDirPath);
		WatchService watcher = 
			FileSystems.getDefault().newWatchService();
		Kind<?>[] watchKinds = { ENTRY_CREATE, ENTRY_MODIFY };
		p.register(watcher, watchKinds);
		threadPool.submit(new DemoService());
		while (!done) {
			WatchKey key = watcher.take();
			for (WatchEvent<?> e : key.pollEvents()) {
				System.out.println(
					"Saw event " + e.kind() + " on " + 
					e.context());
				if (e.context().toString().equals(FILE_SEMA_FOR)) {
					System.out.println("Semaphore found, shutting down watcher");
					done = true;
				}
			}
			if (!key.reset()) {
				System.err.println("WatchKey failed to reset!");
			}
		}
	}

	/**
	 * Nested class whose only job is to wait a while, create a file in
	 * the monitored directory, and then go away.
	 */
	private final static class DemoService implements Runnable {
		public void run() {
			try {
				Thread.sleep(1000);
				System.out.println("DemoService: Creating file");
				Files.deleteIfExists(SEMAPHORE_PATH); // clean up from previous run
				Files.createFile(SEMAPHORE_PATH);
				Thread.sleep(1000);
				System.out.println("DemoService: Shutting down");
			} catch (Exception e) {
				System.out.println("Caught UNEXPECTED " + e);
			}
		}
	}
}
// end::main[]
