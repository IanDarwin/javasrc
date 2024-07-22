// package network;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

final boolean DEBUG = true;

/**
 * My simpler-re-implementation of Baeldung's Java port scanner
 * https://www.baeldung.com/java-port-scanning
 */
void main() throws Exception {
	List<Integer> open = runPortScan("127.0.0.1", 32000);
	open.stream().map(d->String.format("%d ", d)).forEach(System.out::print);
	System.out.println();	// end with a newline

}

final static int timeOut = 15 * 1000;
int failedPorts = 0;

public List<Integer> runPortScan(String ip, int nbrPortMaxToScan) throws Exception {
        Vector<Integer> openPorts = new Vector<>();
        ExecutorService executorService;
		// executorService = Executors.newFixedThreadPool(500);
		executorService = Executors.newVirtualThreadPerTaskExecutor();
		for (int i = 1; i < nbrPortMaxToScan; i++) {
				final int currentPort = i;
                executorService.submit(() -> {
						if (DEBUG) {
							System.out.println(currentPort);
						}
                        try (Socket socket = new Socket(ip, currentPort)) {
								socket.setSoTimeout(timeOut);
                                socket.close();
                                openPorts.add(currentPort);
								System.out.println(ip + ", port open: " + currentPort);
                        } catch (ConnectException e) {
								if (DEBUG) {
									System.out.printf("Connect to %d failed : %s\n", currentPort, e);
								}
                                ++failedPorts;
						} catch (IOException e) {
								System.out.printf("Connect to %d failed : %s\n", currentPort, e);
								System.exit(1);
                        }
                });
        }
        executorService.shutdown();
        try {
                executorService.awaitTermination(3, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
                throw new RuntimeException(e);
        }
		Collections.sort(openPorts);
		return openPorts;
}
