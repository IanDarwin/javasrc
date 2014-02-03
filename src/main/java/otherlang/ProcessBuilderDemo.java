package otherlang;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ProcessBuilderDemo shows how to execute an external 
 * program (in this case the MS-Windows notepad program).
 */
public class ProcessBuilderDemo {

    public static void main(String argv[]) throws Exception {
        // BEGIN main
        List<String> command = new ArrayList<>();            // <1>
        command.add("notepad");
        command.add("foo.txt");
        ProcessBuilder builder = new ProcessBuilder(command);// <2>
        builder.environment().put("PATH", 
                "/windows;/windows/system32;/winnt");        // <3>
        final Process godot = builder.directory(
            new File(System.getProperty("user.home"))).      // <4>
            start();
        System.err.println("Waiting for Godot");             // <5>
        godot.waitFor();                                     // <6>
        // END main

        System.out.println("Program terminated!");
        return;
    }
}
