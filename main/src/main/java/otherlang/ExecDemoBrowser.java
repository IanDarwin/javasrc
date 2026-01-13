package otherlang;

import java.awt.Container;
import java.awt.FlowLayout;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * ExecDemoBrowser shows how to execute a program from within Java.
 */
// tag::main[]
public class ExecDemoBrowser {
	private static final long serialVersionUID = 1;
	
	Logger logger = Logger.getLogger(ExecDemoBrowser.class.getName());

	/** The name of the browser. */
	private static final String BROWSER = "firefox";
	/** The name of the help file. */
	protected final static String HELP_FILE = "/help/index.html";
	/** The JFrame */
	protected final JFrame jf;

	/** main - instantiate and run */
	public static void main(String av[]) throws Exception {
		String program = av.length == 0 ? BROWSER : av[0];
		new ExecDemoBrowser(program);
	}

	/** The name of the binary executable that we will run */
	protected static String program;

	/** Constructor - set up strings and things. */
	public ExecDemoBrowser(String program) {
		jf = new JFrame("ExecDemoBrowser: " + program + " edition");
		ExecDemoBrowser.program = program;

		Container cp = jf.getContentPane();
		cp.setLayout(new FlowLayout());
		JButton b;
		cp.add(b=new JButton("Exec"));
		b.addActionListener(e -> runProgram());
		cp.add(b=new JButton("Wait"));
		b.addActionListener(e -> doWait());
		cp.add(b=new JButton("Exit"));
		b.addActionListener(e -> System.exit(0));
		jf.pack();
		jf.setLocation(400, 300);
		jf.setVisible(true);
	}

	Process process;
	ExecutorService threadPool = Executors.newFixedThreadPool(1);

	/** Start the help, in its own Thread. */
	public void runProgram() {

		threadPool.submit(() -> {
				try {
					// Get a "file:" URL for the Help File
					URL helpURL = ExecDemoBrowser.this.getClass().getResource(HELP_FILE);
					if (helpURL == null) {
						JOptionPane.showMessageDialog(jf,
							"Unable to find Help File in resource path",
							"Error",
							JOptionPane.ERROR_MESSAGE);
						System.exit(1);
					}

					// Start the external browser from the Java Application.

					String osName = System.getProperty("os.name");
					String run;
					if ("Mac OS X".equals(osName)) {
						run = "open -a " + program;
						// Any other OSes needing special handling?
					} else {
						run = program;
					}

					process = Runtime.getRuntime().exec(
						new String[]{run, helpURL.toString()});

					logger.info("In main after exec .");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(jf,
						"Error" + ex, "Error",
						JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();	// In terminal window, if any
				}
			});
	}

	public void doWait() {
		if (process == null) {
			logger.info("Nothing to wait for.");
			return;
		}
		logger.info("Waiting for process " + process);
		try {
			process.waitFor();
			// wait for process to complete 
			// (may not work as expected for some old Windows programs)
			logger.info("Process " + process + " is done.");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(jf,
				"Error" + ex, "Error",
				JOptionPane.ERROR_MESSAGE);
		}
	}

}
// end::main[]
