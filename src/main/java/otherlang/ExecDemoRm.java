import java.io.*;

/*
 I will create three temp files, ls them, and rm them
*/
public class Wildcards {
	public static void main(String[] args) throws IOException {
		for (int i=0; i<10; i++) {
			new File("/tmp/ww" + i).createNewFile();
		}
		Runtime.getRuntime().exec("ls -l /tmp/ww? > /tmp/report");
		// Runtime.getRuntime().exec("rm -f /tmp/ww?");
	}
}
