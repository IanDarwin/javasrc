package graphics;

import java.awt.Canvas;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class ShowOnAllScreens {
	public static void main(String[] args) {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] screenDevices = ge.getScreenDevices();
		for (GraphicsDevice gd : screenDevices) {
			System.out.println(gd + (gd == ge.getDefaultScreenDevice() ? " (default)" : ""));
			GraphicsConfiguration[] gc = gd.getConfigurations();
			// Might want to loop to find largets or bests, instead of taking first one
			GraphicsConfiguration g = gc[0];
			JFrame jf = new JFrame(gd.getDefaultConfiguration());
			Canvas c = new Canvas(g);
			Rectangle gcBounds = g.getBounds();
			System.out.println("SIZE: " + gcBounds);
			jf.getContentPane().add(c);
			jf.setSize(gcBounds.width, gcBounds.height);
			jf.setVisible(true);
		}
	}
}
