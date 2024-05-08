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
	
	System.out.println("Client Starting");
	UnixDomainSocketAddress sockAddr = UnixDomainSocketAddress.of(socketPath);
	String message = "Hello via a UNIX Domain Socket";
	try (SocketChannel channel = SocketChannel.open(StandardProtocolFamily.UNIX)) {
		channel.connect(sockAddr);
		ByteBuffer outBuf = ByteBuffer.allocate(BUFSIZE);
		outBuf.clear();
		outBuf.put(message.getBytes());
		outBuf.flip();
		System.out.printf("[Sending] %s\n", message);
		while (outBuf.hasRemaining()) {
			channel.write(outBuf);
		}
	} catch (Exception ex) {
		throw new RuntimeException("Send failed: " + ex, ex);
	}
	// end::main[]
}
