package starting;

/** Test for presence of Swing on classpath at runtime.
 */
public class TestForSwing {
	public static void main(String[] args) {
		try {
			Class.forName("javax.swing.JButton");
		} catch (ClassNotFoundException e) {
			String failure = 
				"Sorry, but this version of MyApp needs \n" +
				"a Java Runtime with JFC/Swing components\n" +
				"having the final names (javax.swing.*)";
			System.err.println(failure);
			// Make something appear in the GUI. Either a JOptionPane, or:
			// myPanel.add(new Label(failure));
			throw new IllegalArgumentException(failure);
		}
		// No need to print anything here - the GUI should work...
	}
}
