package jaas;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * This program demonstrates use of JAAS with a login configuration
 * and a policy file; the intent in runDemo is that, after logging in OK,
 * the user will be able to read but not write files on the local disk store.
 * <p>
 * <b>NOT WORKING</b>
 * @version $Id$
 */
public class JaasDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String loginConfig = System.getProperty("java.security.auth.login.config");
		if (!"jaas/loginconfig.txt".equals(loginConfig)) {
			System.err.println("login.config not set right, is " + loginConfig);
		}

		final Object securityConfig = System.getProperty("java.security.policy");
		if (!"jaas/permissions.txt".equals(securityConfig)) {
			System.err.println("policy config not right, is " + securityConfig);
		}

		new JaasDemo();
	}

	private JFrame theFrame;

	@SuppressWarnings("serial")
	JaasDemo() {
		System.out.println("JaasDemo.JaasDemo()");
		theFrame = new JFrame("JAAS Demo");
		theFrame.setLayout(new FlowLayout());
		Action loginAction = new AbstractAction("Login") {
			public void actionPerformed(ActionEvent e) {
				runDemo();
			}
		};
		theFrame.add(new JButton(loginAction));

		Action quitAction = new AbstractAction("Exit") {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		theFrame.add(new JButton(quitAction));
		theFrame.setBounds(200, 300, 200, 100);
		theFrame.setVisible(true);
	}

	void runDemo() {
		System.out.println("JaasDemo.runDemo()");
		try {
			CallbackHandler loginCallback = new LoginDialogWindow();
			((JDialog)loginCallback).setVisible(true);
			LoginContext loginContext = new LoginContext("JaasDemo", loginCallback);
			loginContext.login();
			// If the call to login() doesn't throw an exception, we're in!
			Subject subject = loginContext.getSubject();
			JOptionPane.showMessageDialog(theFrame,
					String.format("Congratulations %s, you are logged in!", subject),
					"Welcome", JOptionPane.INFORMATION_MESSAGE);

			// Now set the security manager to control I/O
			// (can't set it sooner because then you won't have
			// permission to create the login context...)
			System.setSecurityManager(new SecurityManager());

			// Should be able to read
			new FileReader(".");
			System.out.println("Successfully opened reader");

			// Should not be able to write:
			new FileWriter("./jnk.txt");
			JOptionPane.showMessageDialog(theFrame,
				"Egad; I was allowed to write a file!", "Whoops!",
				JOptionPane.ERROR_MESSAGE);

		} catch (LoginException e) {
			JOptionPane.showMessageDialog(theFrame,
					"Login Failed!\n" + "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(theFrame,
					"Read Failed unexpectedly!\n" + "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(theFrame,
					"Whew! Was not allowed to write:\n" + e, "Normal end", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	class LoginDialogWindow extends JDialog implements CallbackHandler {

		private static final long serialVersionUID = -5101283531619214053L;

		public LoginDialogWindow() {
			super(theFrame, "Dummy Login", true);
			setLayout(new GridLayout(0, 2, 5, 5));
			add(new JLabel("UserName:"));
			add(new JTextField(20));
			add(new JLabel("Password:"));
			add(new JPasswordField(20));
			add(new JButton("OK"));
			add(new JButton("Cancel"));
			pack();
		}

		public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
			System.out.println("LoginDialogWindow.handle()");
			for (Callback c : callbacks) {
				System.out.println(c);
			}
		}
	}
}
