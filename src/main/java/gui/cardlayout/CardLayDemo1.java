package gui.cardlayout;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/** Simpler CardLayout demo: cycles through some labels.
 * @author Ian Darwin
 */
public class CardLayDemo1 extends Applet {
	CardLayout cardlay;
        Panel panel;
	Button b1;
	int cardno = 0;
	final int NCARDS = 4;
	String labels[] = new String[NCARDS];

	public void init() {

		panel = new Panel();
		cardlay = new CardLayout();
		b1 = new Button("Next");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (++cardno >= NCARDS)
					cardno = 0;
				cardlay.show(panel, labels[cardno]);
			}
		});
		labels[0] = "Card One";
		labels[1] = "Card Two";
		labels[2] = "Card Three";
		labels[3] = "Card Four";

		panel.setLayout(cardlay);
		for (int i=0; i<NCARDS; i++)
			panel.add(labels[i], new Label(labels[i]));
		cardlay.show(panel, labels[0]);

		setLayout(new BorderLayout());
		add("Center", panel);
		add("South", b1);
	}
}
