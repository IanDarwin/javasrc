/**
 * Download a set of files from a URL containing the list and their checksums.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class Download {
	public static void main(String argv[]) {
		//+
		System.out.println("Download starting.");
		getListFromURL();
		getListFromDirectory();
		downloadNeededFiles();
		System.out.println("Download done.");
		//-
	}

	class MyURLStreamFactory() {
		getStream(filename f) {
			// TODO error handling
			return new URL(base, f).getInputStream();
		}
	}
}
