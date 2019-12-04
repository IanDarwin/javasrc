package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Creates an image, adds some text, and writes it out to a file.
 * In fact, it creates a bunch of files, for use as icons in the Java Cookbook.
 * @author Ian Darwin
 */
public class CreateAndSaveImage {
	final static int VERSIONS[] = { 5,6,7,8};
	final static String DIRECTORY = "images" + File.separatorChar;

	public static void main(String[] args) throws Exception {
		String dir = DIRECTORY;
		for (int v : VERSIONS) {
			BufferedImage image =
				new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = image.createGraphics();
			g.setColor(Color.black);
			g.setStroke(new BasicStroke(8f));
			g.drawOval(4, 0, 248, 248);
			Font f = new Font("Serif", Font.BOLD, 160);
			g.setFont(f);
			String bigNumberLabel = Integer.toString(v);
			Rectangle2D lineMetrics = f.getStringBounds(bigNumberLabel, g.getFontRenderContext() );
			int x = (int) ((image.getWidth() - lineMetrics.getWidth() ) / 2);
			int y = (int) ((image.getHeight() + lineMetrics.getHeight()) / 2) -20;
			g.drawString(bigNumberLabel, x, y);
			ImageIO.write(image, "png", new File(String.format("%sjava%d.png",dir, v)));
		}
		System.exit(0);	// Code starts GUI thread...
	}
}
