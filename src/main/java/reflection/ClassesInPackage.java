package reflection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Get a list of classes in a given package name.
 * You can't really do this in Java, in every case; this handles some cases only.
 * @author Ian Darwin
 */
// BEGIN main
public class ClassesInPackage {

	/** This approach began as a contribution by Paul Kuit at
	 * http://stackoverflow.com/questions/1456930/, but his only
	 * handled single files in a directory in classpath, not in Jar files.
	 * N.B. Does NOT handle system classes!
	 * @param packageName
	 * @return
	 * @throws IOException
	 */
	public static String[] getPackageContent(String packageName)
		throws IOException {

		final String packageAsDirName = packageName.replace(".", "/");
		final List<String> list = new ArrayList<>();
		final Enumeration<URL> urls = 
				Thread.currentThread().
				getContextClassLoader().
				getResources(packageAsDirName);
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			// System.out.println("URL = " + url);
			String file = url.getFile();
			switch (url.getProtocol()) {
			case "file":
				// This is the easy case: "file" is 
				// the full path to the classpath directory
				File dir = new File(file);
				for (File f : dir.listFiles()) {
					list.add(packageAsDirName + "/" + f.getName());
				}
				break;
			case "jar":
				// This is the harder case; "file" is of the form
				// "jar:/home/ian/bleah/darwinsys.jar!com/darwinsys/io"
				// for some jar file that contains at least one class from 
				// the given package.
				int colon = file.indexOf(':');
				int bang = file.indexOf('!');
				String jarFileName = file.substring(colon + 1, bang);
				JarFile jarFile = new JarFile(jarFileName);
				Enumeration<JarEntry> entries = jarFile.entries();
				while (entries.hasMoreElements()) {
					JarEntry e = entries.nextElement();
					String jarEntryName = e.getName();
					if (!jarEntryName.endsWith("/") &&
						jarEntryName.startsWith(packageAsDirName)) {
						list.add(jarEntryName);
					}
				}
				break;
			default:
				throw new IllegalStateException(
				"Dunno what to do with URL " + url);
			}
		}
		return list.toArray(new String[] {});
	}
	
	public static void main(String[] args) throws IOException {
		String[] names = getPackageContent("com.darwinsys.io");
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println("Done");
	}
}
// END main
