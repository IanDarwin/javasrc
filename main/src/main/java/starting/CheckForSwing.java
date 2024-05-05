package starting;

/** Test for presence of Swing on classpath at runtime.
 */
// tag::main[]
public class CheckForSwing {
	public static void main(String[] args) {
		try {
			Class.forName("javax.swing.JButton");
		} catch (ClassNotFoundException e) {
			String failure = 
				"Sorry, but this version of MyApp needs \n" +
				"a Java Runtime with javax.swing GUI components.\n" +
				"Please check your Java installation and try again.";
			// Better to make something appear in the GUI. Either a 
			// JOptionPane, or: myPanel.add(new Label(failure));
			System.err.println(failure);
		}
		// No need to print anything here - the GUI should work...
	}
}
// end::main[]
