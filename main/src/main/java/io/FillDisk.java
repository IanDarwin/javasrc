package io;

import java.io.*;

/** FillDisk writes until the disk is full.
 * Like any tool, can be used for evil (local DoS attack) or good (writing
 * over blocks after you remove an important file).
 */
public class FillDisk {
	public static void main(String[] args) throws IOException {
		File f = File.createTempFile("fill", "disk");
		OutputStream os = new FileOutputStream(f);
		System.out.println("Filename is " + f.getAbsolutePath());
		byte[] data = new byte[65535];
		while (os != null) {
			try {
				os.write(data);
			} catch (IOException e) {
				System.out.println(e);
				os.close();
				f.delete();
				os = null;
			}
		}
	}
}
