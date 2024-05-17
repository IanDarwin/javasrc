package network;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

// tag::main[]
public class VirtualThreadedWebServer extends WebServer0 {

	ExecutorService threadpool = Executors.newVirtualThreadPerTaskExecutor();

	/** RunServer accepts connections and passes each one to a handler. */
	@Override
	public void runServer(int port) throws Exception {
		ServerSocket s = getServerSocket(port);
		while (true) {
			try {
				Socket us = s.accept();	// blocking
				threadpool.submit(() -> handle(us));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
// end::main[]
