package otherlang;

import java.awt.Container;
import java.awt.FlowLayout;
import java.net.URL;
import java.util.Stack;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * ExecDemoNS shows how to execute a program from within Java.
 */
// tag::main[]
public class ExecDemoNS extends JFrame {
	private static final long serialVersionUID = 1;
	private static final String BROWSER = "firefox";
	
	Logger logger = Logger.getLogger(ExecDemoNS.class.getSimpleName());

	/** The name of the help file. */
	protected final static String HELPFILE = "./help/index.html";

	/** A stack of process objects; each entry tracks one external running process */
	Stack<Process> pStack = new Stack<>();

	/** main - instantiate and run */
	public static void main(String av[]) throws Exception {
		String program = av.length == 0 ? BROWSER : av[0];
		new ExecDemoNS(program).setVisible(true);
	}

	/** The path to the binary executable that we will run */
	protected static String program;

	/** Constructor - set up strings and things. */
	public ExecDemoNS(String program) {
		super("ExecDemo: " + program);
		ExecDemoNS.program = program;

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		JButton b;
		cp.add(b=new JButton("Exec"));
		b.addActionListener(e -> runProgram());
		cp.add(b=new JButton("Wait"));
		b.addActionListener(e -> doWait());
		cp.add(b=new JButton("Exit"));
		b.addActionListener(e -> System.exit(0));
		pack();
	}

	/** Start the help, in its own Thread. */
	public void runProgram() {

		new Thread() {
			public void run() {

				try {
					// Get a "file:" URL for the Help File
					URL helpURL = this.getClass().getClassLoader().
						getResource(HELPFILE);

					// Start the external browser from the Java Application.

					String osname = System.getProperty("os.name");
					String run;
					if ("Mac OS X".equals(osname)) {
						run = "open -a " + program;
						// "if" allows for other OSes needing special handling
					} else {
						run = program;
					}

					pStack.push(Runtime.getRuntime().exec(new String[]{run, helpURL}));

					logger.info("In main after exec " + pStack.size());

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(ExecDemoNS.this,
						"Error" + ex, "Error",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		}.start();

	}

	public void doWait() {
		if (pStack.size() == 0) {
			logger.info("Nothing to wait for.");
			return;
		}
		logger.info("Waiting for process " + pStack.size());
		try {
			Process p = pStack.pop();
			p.waitFor();
			// wait for process to complete 
			// (may not work as expected for some old Windows programs)
			logger.info("Process " + p + " is done.");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
				"Error" + ex, "Error",
				JOptionPane.ERROR_MESSAGE);
		}
	}

}
// end::main[]
