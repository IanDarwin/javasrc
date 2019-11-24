package email;

import javax.swing.*;

/** CustMailer -- to be a small Mailing List sender. */
public class CustMailer extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] argv) {
		new CustMailer().setVisible(true);
	}
	public CustMailer() {
		JTabbedPane jtp = new JTabbedPane();
		setContentPane(jtp);
		jtp.addTab("Sending", new JPanel());
		jtp.addTab("Editing", new JPanel()); 
		
	}

}
