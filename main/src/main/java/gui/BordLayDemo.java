package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.util.BitSet;

/** BorderLayout demo. Also shows off BitSet class from java.util.
 * <P>Do <B>not</B> assume from this demo that BorderLayout forces
 * e.g., East and West to be same width, or North and South to be
 * same height. See BordLayDemo2 for counter examples.
 */
public class BordLayDemo extends Frame {
	String cp[] = { "", "North", "East", "South", "West", "Center" };
	static final int   NORTH=1, EAST=2, SOUTH=3, WEST=4, CENTER=5;

	BordLayDemo(String s, BitSet b) {
		super(s);
		setLayout(new BorderLayout());
		for (int i = 1; i<=5; i++)
			if (b.get(i))
				add(new Button(cp[i]), cp[i]);
		pack();
	}

	public static void main(String[] av) {
		BitSet b = new BitSet();
		b.set(NORTH);
		b.set(EAST);
		b.set(SOUTH);
		b.set(WEST);
		b.set(CENTER);
		new BordLayDemo("All", b).setVisible(true);

		b.clear(CENTER);
		new BordLayDemo("Compass", b).setVisible(true);

		b.set(CENTER);
		b.clear(NORTH);
		b.clear(SOUTH);
		new BordLayDemo("Horizontal", b).setVisible(true);
	}
}

