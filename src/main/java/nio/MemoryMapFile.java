package nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MemoryMapFile {

	/**
	 * Read a file by NIO and print it
	 */
	public static void main(String[] args) throws IOException {
		String file = "nio/MemoryMapFile.java";
		try (FileInputStream fi = new FileInputStream(file);
				FileChannel fc = fi.getChannel()) {
			long size = fc.size();
			MappedByteBuffer bytes = fc.map(
					FileChannel.MapMode.READ_ONLY, 0, size);
			byte[] data = new byte[(int) size];
			bytes.get(data, 0, (int) size);
			System.out.println(new String(data));
		}
	}
}
