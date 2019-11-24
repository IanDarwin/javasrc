package otherlang;

import java.io.*;

/*
 * Show how to use exec to pass complex args (which are almost
 * certainly system-dependant) to a command-line interpreter.
 */
public class ExecShellArgs {
	public static void main(String[] args) throws IOException {
		Runtime r = Runtime.getRuntime();
		String[] nargs = { "sh", "-c", "for i in 1 2 3; do echo $i; done" };
		Process p = r.exec(nargs);
		BufferedReader is =
			new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = is.readLine()) != null)
			System.out.println(line);
	}
}
