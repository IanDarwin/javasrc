import com.ibm.cs.util.*;
import com.ibm.bsf.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.darwinsys.util.*;

/** Longer sample of using Bean Scripting Framework with JPython */
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
			manager.registerScriptingEngine("jpython",
			  "com.ibm.bsf.engines.jpython.JPythonEngine", fntypes);
			jpythonengine = manager.loadScriptingEngine("jpython");

			// Tell BSF about the bean.
			manager.declareBean("bean", bean, LabelText.class);

			// Read the script file into BSF
			language = manager.getLangFromFilename(FILENAME);
			script = IOUtils.getStringFromReader(
				new FileReader(FILENAME));

		} catch (Exception ex) {
			System.err.println(ex.toString());
			System.exit(0);
		}

		System.out.println("Scripting setup done, building GUI.");

		final JFrame jf = new JFrame(getClass().getName());

		Container cp = jf.getContentPane();
		cp.setLayout(new FlowLayout());

		cp.add(bean);			// add the LabelText

		JButton b = new JButton("Click me!");
		cp.add(b);				// and the button under it.
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
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
			}
		});

		// A Quit button at the bottom
		JButton qb = new JButton("Quit");
		cp.add(qb);
		qb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});

		// Routine JFrame setup
		jf.pack();
		jf.setVisible(true);
	}
}
