import java.io.*;

/**
 * FNFilter - directory lister using FilenameFilter
 *
 * @author Ian Darwin
 */

class OnlyGfx implements FilenameFilter {
	public boolean accept(File dir, String s) {
		if (s.endsWith(".gif"))
			return true;
		// others: jpeg, eps, etc?
		return false;
	}
}
public class FNFilter {
	public static void main(String av[]) {
		String objects[] = (new File(".")).list(new OnlyGfx());

		for (int i=0; i<objects.length; i++)
			System.out.println(objects[i]);
	}
}
