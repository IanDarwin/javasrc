import java.awt.*;
import java.awt.event.*;

/**
 * YesNoDialog - put up a Yes/No/Cancel dialog, with callbacks
 *
 * This version has three buttons, and a callback on each one.
 *
 * @deprecated Use javax.swing.JOptionPane instead
 *
 * @author Ian Darwin, Learning Tree.
 */
public class YesNoDialog extends Dialog implements ActionListener{
	protected Frame parent;
	protected Button yB, nB, cB;	// yes, no cancel
	protected Label msgLab;

	/** Constructor for a YesNoDialog.
	 * @param	parent	Frame that is to own this dialog
	 * @param	title	String to display in titlebar.
	 * @param	msg	String to display in the dialog itself.
	 * @param	yesLab	String for Yes button
	 * @param	noLab	String for No button
	 * @param	canLab	String for Cancel button
	 */
	public YesNoDialog(Frame parent, String title, String msg,
			String yesLab, String noLab, String canLab) {
		super(parent, title, true);	// modal
		this.parent = parent;
		setLayout(new BorderLayout(10, 10));
		msgLab = new Label(msg, Label.CENTER);
		add(msgLab, BorderLayout.NORTH);

		Panel p = new Panel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		if (yesLab != null) {
			p.add(yB = new Button(yesLab));
			yB.addActionListener(this);
		}
		if (noLab != null) {
			p.add(nB = new Button(noLab));
			nB.addActionListener(this);
		}
		if (canLab != null) {
			p.add(cB = new Button(canLab));
			cB.addActionListener(this);
		}
		add(p, BorderLayout.SOUTH);
		pack();
	}

	/** The action handler figures out which button was pressed,
	 * and calls the appropriate callback routine.
	 */
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if (!(obj instanceof Button))
			return;
		if (obj == yB)
			yes();
		if (obj == nB)
			no();
		if (obj == cB)
			cancel();
		setVisible(false);
		dispose();
	}

	/** The yes method does nothing, we expect you will override it. */
	protected void yes() {
	}
	/** The no method does nothing, we expect you will override it. */
	protected void no() {
	}
	/** The cancel method does nothing, we expect you will override it. */
	protected void cancel() {
	}
}
