package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// tag::main[]
class WriteZipFile {

	public static final String FILENAME = "temp.zip";

	public static void main(String[] args) throws Exception {
		File file = new File(FILENAME);
		ZipOutputStream zf = new ZipOutputStream(new FileOutputStream(file));
		zf.putNextEntry(new ZipEntry("foo.bar.txt"));
		zf.write("Hello\n".getBytes());
		zf.putNextEntry(new ZipEntry("WriteZipFile.java"));
		Files.copy(
				Path.of("main/src/main/java/io/WriteZipFile.java"), zf);
		zf.closeEntry();
		zf.close();
		System.out.println("Written to " + FILENAME);
	}
}
// end::main[]
