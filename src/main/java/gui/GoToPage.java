import java.awt.*;
import java.awt.event.*;

/** Implement a simple "Go To Page" dialog
 * Row one: "Go to Page", textfield
 * second OK, Cancel buttons.
 */
public class GoToPage extends Dialog {
	/** TextField used to enter the number */
	protected TextField tf;
	/** The OK button */
	protected Button ok;
	/** The cancel button */
	protected Button can;

	/** Construct a GoToPage window (no actions yet) */
	public GoToPage(Frame f, String title) {
		super(f);
		setTitle(title);

		Label l = new Label("Page Number:");
		tf = new TextField(4);
		tf.setText("1");
		// set the text initially selected so you can easily overtype it
		tf.selectAll();

		ok = new Button("OK");
		can = new Button("Cancel");

		Panel top = new Panel();
		top.add(l);
		top.add(tf);

		Panel bottom = new Panel();
		bottom.add(ok);
		bottom.add(can);

		add(BorderLayout.NORTH, top);
		add(BorderLayout.SOUTH, bottom);

		pack();
	}

	public static void main(String unused[]) {
		final Frame f = new Frame("Page Dialog Test");
		Button b;
		f.add(b = new Button("Show Dialog"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GoToPage(f, "GoToPage Demo").setVisible(true);
			}
		});
		f.pack();
		f.setVisible(true);
	}
}
