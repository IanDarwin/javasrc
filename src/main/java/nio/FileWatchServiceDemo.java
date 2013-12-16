package nio;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/** Demonstrate the JavaSE 7+ NIO WatchService.
 * 
 * P L E A S E   R E A D   B E F O R E   C O M P L A I N I N G
 * This class absolutely requires Java SE 7+, so just add an exclusion rule
 * (Build Path -> Exclude) if you are living with a legacy version of Java SE.
 */
// BEGIN main
public class FileWatchServiceDemo {

	final static String tempDirPath = "/tmp";
	static Thread mainRunner;
	static volatile boolean done = false;

	public static void main(String[] args) throws Throwable {
		String tempDirPath = "/tmp";
		System.out.println("Starting watcher for " + tempDirPath);
		Path p = Paths.get(tempDirPath);
		WatchService watcher = 
			FileSystems.getDefault().newWatchService();
		Kind<?>[] watchKinds = { ENTRY_CREATE, ENTRY_MODIFY };
		p.register(watcher, watchKinds);
		mainRunner = Thread.currentThread();
		new Thread(new DemoService()).start();
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

	static class DemoService implements Runnable {
		public void run() {
			try {
				Thread.sleep(1000);
				System.out.println("Creating file");
				new File(tempDirPath + "/MyFileSema.for").createNewFile();
				Thread.sleep(1000);
				System.out.println("Stopping WatcherServiceDemo");
				done = true;
				Thread.sleep(1500);
				mainRunner.interrupt();
			} catch (Exception e) {
				System.out.println("Caught UNEXPECTED " + e);
			}
		}
	}
}
// END main
