package dir_file;

import java.io.File;

/** The collected mkdir examples (JavaCook 2e, Recipe 11.9).
 */
public class MkDirExamples {
	public static void main(String[] args) {
		boolean status;
		status = new File("/home/ian/bin").mkdir();
		report(status);
		status = new File("/home/ian/src").mkdir();
		report(status);

		status = new File("/home/ian/once/twice/again").mkdir();	// should fail
		report(status);
		status = new File("/home/ian/once/twice/again").mkdirs();	// should succeed
		report(status);
	}

	static void report(boolean b) {
		System.out.println(b ? "success" : "failure");
	}
}
