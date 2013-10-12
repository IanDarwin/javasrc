package reflection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import junit.framework.TestCase;

public class APIFormatterTest extends TestCase {
	String fileName;

	/**
	 * To test it for real, create zip file with one class
	 * from the same package, loaded from classpath.
	 */
	protected void setUp() throws Exception {
		super.setUp();
		File file = File.createTempFile("test", ".zip");
		file.deleteOnExit();
		fileName = file.getAbsolutePath();
		ZipOutputStream zf = new ZipOutputStream(new FileOutputStream(file));
		zf.putNextEntry(new ZipEntry("foo.bar"));
		zf.write("Hello".getBytes());
		zf.putNextEntry(new ZipEntry("reflection/GetAndInvokeMethod.class"));
		InputStream is =
			GetAndInvokeMethod.class.getResourceAsStream("GetAndInvokeMethod.class");
		int b = 0;
		while((b = is.read()) != -1) {
			zf.write((byte)b);
		}
		is.close();
		zf.closeEntry();
		zf.close();
	}

	Class<?> x = null;
	int nClasses = 0;
	public final void testProcessOneZip() throws Exception {
		APIFormatter target = new APIFormatter() {
			@Override
			protected void doClass(Class<?> c) throws IOException {
				++nClasses;
				x = c;
			}
		};
		target.processOneZip(fileName);
		assertEquals(x, GetAndInvokeMethod.class);
		assertEquals(1, nClasses);
		System.out.println("Pass!");
	}

}
