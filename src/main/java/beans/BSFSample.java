import com.ibm.cs.util.*;
import com.ibm.bsf.*;
import java.io.*;

/** Sample of using Bean Scripting Framework with JPython */
public class BSFSample {
	public static void main(String[] args) {
		BSFManager manager = new BSFManager();

		// register scripting language
		String[] fntypes = { ".py" };
		manager.registerScriptingEngine("jpython",
			"com.ibm.bsf.engines.jpython.JPythonEngine", fntypes);

		try {
			BSFEngine jpythonengine = manager.loadScriptingEngine("jpython");

			// try an expression 
			Object r = manager.eval("jpython", "testString", 0, 0, "22.0/7");
			System.out.println("Result type is " + r.getClass().getName());
			System.out.println("Result value is " + r);
		} catch (Exception ex) {
			System.err.println(ex.toString());
		}
		System.out.println("Scripting demo done.");
		return;
	}
}
