package gui;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Testbed for experimentation with modal and non-modal JDialogs.
 * Note that a null-parent modal dialog can become hidden behind
 * the main window; this should be avoided!
 */
public class JDialogIssues {
	@SuppressWarnings("serial")
	static class TestAction extends AbstractAction {
		Frame parent;
		boolean modal;
		JDialog dlg;

		public TestAction(Frame parent, boolean modal) {
			this.parent = parent;
			this.modal = modal;
			dlg = new JDialog(parent, modal);
			dlg.add(new JLabel(toString()));
			putValue(Action.NAME, toString());
		}

		@Override
		public String toString() {
			return "Dialog with " + (parent!=null?parent.getName():"null") + " " + modal;
		}
		public void actionPerformed(ActionEvent e) {
			dlg.setVisible(true);
		}
	}
	public static void main(String[] args) {
		JFrame jf = new JFrame("Woo");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new GridLayout(0, 1));
		final TestAction[] data = {
			new TestAction(null, false),
			new TestAction(null, true),
			new TestAction(jf, false),
			new TestAction(jf, true)
		};
		jf.setSize(640, 480);
		for (TestAction testAction : data) {
			jf.add(new JButton(testAction));
		}
		jf.setVisible(true);
	}
}
