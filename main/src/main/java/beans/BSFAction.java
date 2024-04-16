package beans;

import java.awt.Container;
import java.awt.FlowLayout;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.bsf.BSFEngine;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;

import com.darwinsys.io.FileIO;
import com.darwinsys.swingui.LabelText;

/**
 *  Longer sample of using Bean Scripting Framework to call JPython
 * N.B. You MUST have org.python.core.PyJavaInstance on CLASSPATH or
 * this program will go down in flames.
 */
// tag::main[]
public class BSFAction {
	protected String FILENAME = "buttonhandler.py";
	protected BSFManager manager;
	protected BSFEngine jpythonengine;
	protected String language;
	protected String script;

	public static void main(String[] args) {
		new BSFAction();
	}

	BSFAction() {

		// Construct the Bean instance
		LabelText bean = new LabelText("Message to Python script");

		try {
			manager = new BSFManager();

			// register scripting language
			String[] fntypes = { ".py" };
			BSFManager.registerScriptingEngine("jpython",
			  "org.apache.bsf.engines.jpython.JPythonEngine", fntypes);
			jpythonengine = manager.loadScriptingEngine("jpython");

			// Tell BSF about the bean.
			manager.declareBean("bean", bean, LabelText.class);

			// Read the script file into BSF
			language = BSFManager.getLangFromFilename(FILENAME);
			script = FileIO.readerToString(
				new FileReader(FILENAME));

		} catch (Exception ex) {
			System.err.println("Well, that didn't work. Why not? " + ex);
			return;
		}

		System.out.println("Scripting setup done, building GUI.");

		final JFrame jf = new JFrame(getClass().getName());

		Container cp = jf.getContentPane();
		cp.setLayout(new FlowLayout());

		cp.add(bean);			// add the LabelText

		JButton b = new JButton("Click me!");
		cp.add(b);				// and the button under it.
		b.addActionListener(_ -> {
				try {
					// When the button is pressed, run the script.
					System.out.println("Script output: -->");
					manager.exec(language, FILENAME, 0, 0, script);
					System.out.println("<-- End of Script output.");
				} catch (BSFException bse) {
					JOptionPane.showMessageDialog(jf,
						"ERROR: " + bse, "Script Error",
						JOptionPane.ERROR_MESSAGE);
				}
		});

		// A Quit button at the bottom
		JButton qb = new JButton("Quit");
		cp.add(qb);
		qb.addActionListener(_ ->  System.exit(0));

		// Routine JFrame setup
		jf.pack();
		jf.setVisible(true);
	}
}
// end::main[]
