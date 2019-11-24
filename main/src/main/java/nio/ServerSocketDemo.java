package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketDemo {

	public static void main(String[] args) throws IOException {
		ServerSocketChannel srv = ServerSocketChannel.open();
		ServerSocket sock = srv.socket();
		sock.bind(new InetSocketAddress(2300));
		String welcome = "Hello from NIO\nPlease enter your name: ";
		ByteBuffer mbb = ByteBuffer.allocate(welcome.length());
		mbb.put(welcome.getBytes());
		while (true) {
			try (SocketChannel sockChan = srv.accept()) {
				System.out.println("ServerSocketDemo.main(): accepted");
				mbb.rewind();
				while (mbb.hasRemaining()) {
					sockChan.write(mbb);
				}
				byte[] chars = new byte[4096];
				ByteBuffer buff = ByteBuffer.wrap(chars);
				sockChan.read(buff);
				String resp = new String(chars, 0, buff.position());
				System.out.println("User replied: " + resp);
			}
		}
	}

}
