import java.io.*;

/**
 * FNFilter - directory lister using FilenameFilter
 *
 * @author Ian Darwin
 */

public class FNFilter2 implements FilenameFilter {
	public boolean accept(File dir, String s) {
		if (s.endsWith(".gif"))
			return true;
		// others: jpeg, eps, etc?
		return false;
	}

	public void process(String dir) {
		String objects[] = (new File(dir)).list(this);

		for (int i=0; i<objects.length; i++)
			System.out.println(objects[i]);
	}
	public static void main(String av[]) {
		FNFilter2 ff = new FNFilter2();
		ff.process(".");
	}
}
