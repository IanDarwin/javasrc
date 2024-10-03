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
	ServerSocketChannel serverChan = 				// <1>
		ServerSocketChannel.open(StandardProtocolFamily.UNIX);
	UnixDomainSocketAddress sockAddr = 				// <2>
		UnixDomainSocketAddress.of(socketPath);
	serverChan.bind(sockAddr);						// <3>
	System.out.println("Waiting...");
	SocketChannel channel;
	while ((channel = serverChan.accept()) != null) {	// <4>
		ByteBuffer inBuf = ByteBuffer.allocate(BUFSIZE); // <5>
		int numBytes;
		while ((numBytes = channel.read(inBuf)) > 0) {	// <6>
			byte[] bytes = new byte[numBytes];
			inBuf.flip();								// <7>
			inBuf.get(bytes);
			String message = new String(bytes);			// <8>
			System.out.printf("[Incoming] %s\n", message);
			inBuf.clear();
		}
	}
	// end::main[]
}
