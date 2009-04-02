package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/** Demonstrate simple use of JGoodies to do form layout.
 */
public class JGoodiesLayoutDemo {

	/** If true, JGoodies will paint lines showing boundaries */
	boolean debug = false;

	public static void main(String[] args) {
		final JGoodiesLayoutDemo demo = new JGoodiesLayoutDemo();
		demo.setVisible(true);
	}

	private final JFrame jf;
	
	public JGoodiesLayoutDemo() {

		jf = new JFrame("JGoodies Form Demo");
		jf.add(buildMainComponents(), BorderLayout.CENTER);
		jf.add(buildBottomPanel(), BorderLayout.SOUTH);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// Delegation method
	void setVisible(boolean v) {
		jf.setVisible(v);
	}

	private Component buildBottomPanel() {
		
		JPanel panel = new JPanel();
		final JButton okButton = new JButton("OK");
		panel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(new JButton("Cancel"));
		return panel;
	}

	private Container buildMainComponents() {
		FormLayout layout = new FormLayout(
				"right:pref, 4dlu, 50dlu:grow, 4dlu, min", // columns
				"");

		JPanel panel = debug ? new FormDebugPanel() : new JPanel();
		DefaultFormBuilder builder = new DefaultFormBuilder(layout, panel);
		CellConstraints cc = new CellConstraints();
		builder.setDefaultDialogBorder();
		
		builder.append("Add Person");
		builder.nextLine();
		
		builder.appendSeparator("Person");
		JTextField nameTextField = new JTextField();
		builder.append("&Name", nameTextField);
		builder.nextLine();
		JTextField addrTextField = new JTextField();
		builder.append("&Address", addrTextField);
		builder.nextLine();
		
		builder.appendSeparator("Company");
		builder.append("Na&me", new JTextField());
		builder.nextLine();
		builder.append("A&ddress", new JTextField());
		builder.nextRow();
		
		builder.appendSeparator("Status");
		JProgressBar progressBar = new JProgressBar(0,100);
		
		builder.appendRow("pref"); builder.nextRow();
		builder.add(progressBar, cc.xyw(1, builder.getRow(), 3));
		
		return builder.getPanel();
	}
}
