package network;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// tag::main[]
public class VirtualThreadedWebServer extends WebServer0 {

	VirtualThreadedWebServer() throws IOException {
		super();
	}

	public static void main(String[] args) throws Exception {
		new VirtualThreadedWebServer().runServer();
	}

	ExecutorService threadpool = Executors.newVirtualThreadPerTaskExecutor();
	
	/** RunServer accepts connections and passes each one to a handler. */
	@Override
	public void runServer() throws Exception {
		while (!serverSock.isClosed()) {
			try {
				Socket us = serverSock.accept();	// blocking
				threadpool.submit(() -> handle(us));
			} catch(IOException e) {
				e.printStackTrace();
				serverSock.close();
			}
		}
	}
}
// end::main[]
