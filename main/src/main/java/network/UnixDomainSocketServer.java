import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.util.concurrent.*;

final static int BUFSIZE = 1024;

void main() throws IOException {
	// tag::main[]
	System.out.println("Unix Domain Sockets Demo");
	Path socketPath = Path.of("/tmp/.jcb.socket");

	System.out.println("Server side");
	ServerSocketChannel serverChan = ServerSocketChannel.open(StandardProtocolFamily.UNIX);
	UnixDomainSocketAddress sockAddr = UnixDomainSocketAddress.of(socketPath);
	serverChan.bind(sockAddr);
	System.out.println("Waiting...");
	SocketChannel channel = serverChan.accept();
	while (true) {
		try {
			Thread.sleep(250);	// Avoid killing CPU
		} catch (InterruptedException canthappen) {
			// empty
		}
		ByteBuffer inBuf = ByteBuffer.allocate(BUFSIZE);
		var length = channel.read(inBuf);
		if (length < 0) {
			continue;
		}
		byte[] bytes = new byte[length];
		inBuf.flip();
		inBuf.get(bytes);
		String message = new String(bytes);
		System.out.printf("[Incoming] %s\n", message);
	}
	// end::main[]
}
