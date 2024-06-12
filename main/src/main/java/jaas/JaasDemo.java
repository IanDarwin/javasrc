package jaas;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PrivilegedAction;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This program demonstrates use of JAAS with a login configuration
 * and a policy file; the intent in runDemo is that, after logging in OK,
 * the user will be able to read but not write files on the local disk store.
 * <p>
 * N.B. If running under Eclipse, set the Working Directory to
 * ${workspace_loc:javasrc-main}/src/main/java/jaas so it can find the config files.
 * <p>
 * <b>NOT WORKING</b> - login() works (without prompting) but runDemo() fails to read
 */
public class JaasDemo {

	private static final String LOGINCONFIG_FILENAME = "loginconfig.txt";
	
	static Subject subject;
	
	private JFrame theFrame;
	
	public static void main(String[] args) throws Exception {

		if (!new File(LOGINCONFIG_FILENAME).exists()) {
			throw new IOException("Run in dir with loginconfig.txt");
		}

		System.setProperty("java.security.auth.login.config",
				LOGINCONFIG_FILENAME);

		System.setProperty("java.security.policy",
				"permissions.txt");

		new JaasDemo();
	}

	JaasDemo() {
		System.out.println("JaasDemo.JaasDemo()");
		theFrame = new JFrame("JAAS Demo");
		theFrame.setLayout(new FlowLayout());
		Action loginAction = new AbstractAction("Login") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				login();
			}
		};
		theFrame.add(new JButton(loginAction));

		Action runAction = new AbstractAction("Run") {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("removal")
			public void actionPerformed(ActionEvent e) {
				Subject.doAs(subject, new PrivilegedAction<Void>() {

					@Override
					public Void run() {
						runDemo();
						return null;
					}
				});
			}
		};
		theFrame.add(new JButton(runAction));

		Action quitAction = new AbstractAction("Exit") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		theFrame.add(new JButton(quitAction));
		theFrame.setBounds(200, 300, 300, 100);
		theFrame.setVisible(true);
	}

	void login() {
		System.out.println("JaasDemo.login()");
		try {
			//Class.forName("com.sun.security.auth.module.UnixLoginModule");
			// The Unix (and NT?) login modules use the fact that you
			// must be logged in before you can run anything, so they
			// never prompt. But here's a demo of a login prompter anyway.
			LoginContext loginContext =
				new LoginContext("JaasDemo", new MyLoginPrompter());
			System.out.println("Trying to login...");
			loginContext.login();
			// If the call to login() doesn't throw an exception, we're in!
			subject = loginContext.getSubject();
			JOptionPane.showMessageDialog(theFrame,
				
					"Congratulations %s, you are logged in!".formatted(subject),
					"Welcome", JOptionPane.INFORMATION_MESSAGE);
		} catch (LoginException e) {
			JOptionPane.showMessageDialog(theFrame,
					"Login Failed!\n" + "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(theFrame,
					"Could not find login module " + e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
		
	void runDemo() {
		System.out.println("JaasDemo.runDemo()");

		try {

			// Should be able to read
			new FileReader(LOGINCONFIG_FILENAME).close();
			System.out.println("Successfully opened reader");

			// Should not be able to write:
			new FileWriter("./jnk.txt").close(); // Expect constructor to throw
			JOptionPane.showMessageDialog(theFrame,
				"Egad; I was allowed to write a file!", "Whoops!",
				JOptionPane.ERROR_MESSAGE);

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(theFrame,
					"Read Failed unexpectedly!\n" + "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(theFrame,
					"Whew! Was not allowed to write:\n" + e, "Normal end", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class MyLoginPrompter implements CallbackHandler {

		public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
			System.out.println(
					"MyLoginPrompter.handle(): got " + callbacks.length + " callbacks.");
			for (Callback c : callbacks) {
				System.out.println(c);
				if (c instanceof TextOutputCallback callback) {
					// display the message: maybe a prompt...
					System.out.println(callback.getMessage());
				} else if (c instanceof NameCallback nc) {
					String userName = JOptionPane.showInputDialog(nc.getPrompt());
					nc.setName(userName);
				} else if (c instanceof PasswordCallback pc) {
					String password = JOptionPane.showInputDialog(pc.getPrompt());
					pc.setPassword(password.toCharArray());
				} else {
					throw new UnsupportedCallbackException(c,
							"Unrecognized Callback");
				}
			}
		}
	}
}
