package unfinished;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public class GraphicsBounds {
	public static void main(String[] args) {
		Rectangle virtualBounds = new Rectangle();
		GraphicsEnvironment ge = GraphicsEnvironment.
		getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();

		for (int j = 0; j < gs.length; j++) { 

			GraphicsDevice gd = gs[j];
			GraphicsConfiguration[] gc = gd.getConfigurations();

			for (int i=0; i < gc.length; i++) {
				Rectangle tmp = gc[i].getBounds();
				// System.out.println(tmp);
				if (tmp.x != 0 || tmp.y != 0) {
					System.out.println("VIRTUAL: " + tmp);
				}
				virtualBounds = virtualBounds.union(tmp);
			}
		}
		System.out.println(virtualBounds);
	}
}
