package otherlang;

import com.darwinsys.util.Debug;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.io.IOException;

/**
 * ProcessBuilderDemo shows how to execute an external 
 * program (in this case the MS-Windows notepad program).
 */
public class ProcessBuilderDemo {

	public static void main(String argv[]) 
	throws InterruptedException, IOException {
		
		List<String> command = new ArrayList<String>();
		command.add("notepad");
		command.add("foo.txt");
		ProcessBuilder builder = new ProcessBuilder(command);
		Map<String, String> environ = builder.environment();
		environ.put("PATH", "/windows;/windows/system32;/winnt");
		builder.directory(
			new File(System.getProperty("user.home")));

		final Process godot = builder.start();
		
		Debug.println("exec", "Waiting for Godot");
		godot.waitFor();

		System.out.println("Program terminated!");
		return;
	}
}
