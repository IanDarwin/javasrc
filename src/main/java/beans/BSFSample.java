import java.io.*;
import org.apache.bsf.*;
import org.apache.bsf.util.*;

/** Sample of using Bean Scripting Framework with JPython */
public class BSFSample {
	public static void main(String[] args) {
		BSFManager manager = new BSFManager();

		// register scripting language
		String[] fntypes = { ".py" };
		manager.registerScriptingEngine("jython",
			"org.apache.bsf.engines.jython.JythonEngine", fntypes);

		try {
			// try an expression
			Object r = manager.eval("jython", "testString", 0, 0, "22.0/7");
			System.out.println("Result type is " + r.getClass().getName());
			System.out.println("Result value is " + r);
		} catch (Exception ex) {
			System.err.println(ex.toString());
		}
		System.out.println("Scripting demo done.");
		return;
	}
}
