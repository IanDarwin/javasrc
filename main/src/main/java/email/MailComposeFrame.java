package email;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/** A frame for (possibly) multiple MailComposeBean windows.
 */
// tag::main[]
public class MailComposeFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	JDesktopPane dtPane;
	JButton newButton;
	protected int nx, ny;

	/** To be useful here, a MailComposeBean has to be inside
	 * its own little JInternalFrame. 
	 */
	public MailComposeBean newSend() {

		// Make the JInternalFrame wrapper
		JInternalFrame jf = new JInternalFrame();

		// Bake the actual Bean
		MailComposeBean newBean = 
			new MailComposeBean(this, "Compose", 400, 250);

		// Arrange them on the diagonal.
		jf.setLocation(nx+=10, ny+=10);

		// Make the new Bean be the contents of the JInternalFrame
		jf.setContentPane(newBean);
		jf.pack();
		jf.toFront();

		// Add the JInternalFrame to the JDesktopPane
		dtPane.add(jf);
		return newBean;
	}

	/* Construct a MailComposeFrame, with a Compose button. */
	public MailComposeFrame() {

		setLayout(new BorderLayout());

		dtPane = new JDesktopPane();
		add(dtPane, BorderLayout.CENTER);

		newButton = new JButton("Compose");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSend();
			}
		});
		add(newButton, BorderLayout.SOUTH);
	}
}
// end::main[]
