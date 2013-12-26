package beans;

import org.apache.bsf.BSFManager;

/** Sample of using Bean Scripting Framework with JPython */
// BEGIN main
public class BSFSample {
	public static void main(String[] args) {
		BSFManager manager = new BSFManager();

		// register scripting language
		String[] fntypes = { ".py" };
		BSFManager.registerScriptingEngine("jython",
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
// END main
