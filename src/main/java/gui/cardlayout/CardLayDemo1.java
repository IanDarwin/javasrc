
import java.applet.*;
import java.awt.*;

/** Simpler CardLayout demo */
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

	public boolean action(Event e, Object o) {
		if (++cardno >= NCARDS)
			cardno = 0;
		cardlay.show(panel, labels[cardno]);
		return true;
	}
}
