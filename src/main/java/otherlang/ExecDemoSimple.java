package otherlang;

/**
 * ExecDemo shows how to execute an external program and read its output.
 */
public class ExecDemoSimple {
	public static void main(String av[]) throws Exception {
		
		// Run the "notepad" program or a similar editor
		Process p = Runtime.getRuntime().exec("kwrite");

		p.waitFor();
	}
}
