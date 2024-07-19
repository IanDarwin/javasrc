package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class WriteZipFile {

	public static void main(String[] args) throws Exception {
		File file = new File("temp.zip");
		ZipOutputStream zf = new ZipOutputStream(new FileOutputStream(file));
		zf.putNextEntry(new ZipEntry("foo.bar.txt"));
		zf.write("Hello\n".getBytes());
		zf.putNextEntry(new ZipEntry("WriteZipFile.java"));
		String source = Files.readString(Path.of("src/main/java/io/WriteZipFile.java"));
		zf.write(source.getBytes());
		zf.closeEntry();
		zf.close();
	}
}
