package network;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

// tag::main[]
public class ThreadedWebServer extends WebServer0 {

	ThreadedWebServer() throws IOException {
		super();
	}

	public static void main(String[] args) throws Exception {
		new ThreadedWebServer().runServer();
	}

	ExecutorService threadPool = Executors.newCachedThreadPool();

	/** RunServer accepts connections and passes each one to a handler. */
	@Override
	public void runServer() throws Exception {
		while (!serverSock.isClosed()) {
			try {
				Socket us = serverSock.accept();	// blocking
				threadPool.submit(() -> handle(us));
			} catch(IOException e) {
				e.printStackTrace();
				serverSock.close();
			}
		}
	}
}
// end::main[]
