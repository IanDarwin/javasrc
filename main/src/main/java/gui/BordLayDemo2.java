package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.util.BitSet;

/** BorderLayout demo. East and West have different widths, and North
 * and South have different heights.
 */
public class BordLayDemo2 extends Frame {
	String cp[] = { "", "North", "East", "South", "West", "Center" };
	static final int   NORTH=1, EAST=2, SOUTH=3, WEST=4, CENTER=5;
	MyCanvas canvases[] = { null,
		new MyCanvas(cp[NORTH], 100, 150, Color.red),
		new MyCanvas(cp[EAST ], 100, 100, Color.green),
		new MyCanvas(cp[SOUTH], 100, 100, Color.red),
		new MyCanvas(cp[WEST ], 100, 100, Color.blue),
		new MyCanvas(cp[CENTER], 10, 10,  Color.black),
	};

	BordLayDemo2(String s, BitSet b) {
		super(s);
		setLayout(new BorderLayout());
		for (int i = 1; i<=5; i++)
			if (b.get(i))
				add(canvases[i], cp[i]);
		pack();
	}

	public static void main(String[] av) {
		BitSet b = new BitSet();
		b.set(NORTH);
		b.set(EAST);
		b.set(SOUTH);
		b.set(WEST);
		b.set(CENTER);
		new BordLayDemo2("All", b).setVisible(true);

		b.clear(CENTER);
		new BordLayDemo2("Compass", b).setVisible(true);

		b.set(CENTER);
		b.clear(NORTH);
		b.clear(SOUTH);
		new BordLayDemo2("Horizontal", b).setVisible(true);

		b.set(NORTH);
		b.set(SOUTH);
		b.clear(EAST);
		b.clear(WEST);
		new BordLayDemo2("Vertical", b).setVisible(true);
	}
}
