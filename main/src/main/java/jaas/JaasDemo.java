package jaas;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
 * <b>NOT WORKING</b>
 */
public class JaasDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.setProperty("java.security.auth.login.config",
//				"loginconfig.txt");
//
//		System.setProperty("java.security.policy",
//				"permissions.txt");

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
			Class.forName("com.sun.security.auth.module.UnixLoginModule");
			LoginContext loginContext =
				new LoginContext("JaasDemo", new MyLoginPrompter());
			System.out.println("Trying to login...");
			loginContext.login();
			// If the call to login() doesn't throw an exception, we're in!
			Subject subject = loginContext.getSubject();
			JOptionPane.showMessageDialog(theFrame,
					String.format(
						"Congratulations %s, you are logged in!", subject),
					"Welcome", JOptionPane.INFORMATION_MESSAGE);

			// Now set the security manager to control I/O
			// (can't set it sooner because then you won't have
			// permission to create the login context...)
			System.setSecurityManager(new SecurityManager());

			// Should be able to read
			new FileReader(".").close();
			System.out.println("Successfully opened reader");

			// Should not be able to write:
			new FileWriter("./jnk.txt").close(); // Expect constructor to throw
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class MyLoginPrompter implements CallbackHandler {

		public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
			System.out.println(
				"LoginDialogWindow.handle(): got " + callbacks.length + " callbacks.");
			for (Callback c : callbacks) {
				System.out.println(c);
				if (c instanceof TextOutputCallback) {
				      // display the message: maybe a prompt...
				      System.out.println(((TextOutputCallback)c).getMessage());
				    } else if (c instanceof NameCallback) {
				    	NameCallback nc = (NameCallback)c;
				    	String userName = JOptionPane.showInputDialog(nc.getPrompt());
				    	nc.setName(userName);
				    } else if (c instanceof PasswordCallback) {
				    	PasswordCallback pc = (PasswordCallback)c;
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
