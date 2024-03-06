package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

/**
 * Reads an image, adds some text, and writes it back to a new file.
 * In fact, it creates a bunch of files, for use as icons in the Java Cookbook.
 * Doubtful these'll actually get used in the book, but worth knowing how to.
 * Choose number-in-a-circle or number-on-crude-coffee cup by setting "mode".
 * @author Ian Darwin
 */
public class ReadWriteImage {
	/** The mode: IMAGE reads a file, CIRCLE creates a canvas with a gray circle */
	enum Mode { IMAGE, CIRCLE, R_RECTANGLE }
	/** The mode used in this run */
	static Mode mode = Mode.R_RECTANGLE;
	/** Java release to start with */
	final static int START = 8;
	/** Several beyond current Java release, room for expansion */
	final static int END = 25;
	/** Releases we still care about Preview/Incubation in these releases */
	final static int START_PC = 20;
	/** Font size to draw in */
	final static int FONT_SIZE_BIG = 84, FONT_SIZE_NORMAL = 12;
	/** Radius, only used if mode == CIRCLE */
	final static int RADIUS = 160;
	/** Width, height for Rounded Rectangle */
	final static int WIDTH=40, HEIGHT=30;
	final static String DIRECTORY = "v";

	public static void main(String[] args) throws Exception {
		String dir = DIRECTORY;
		IntStream.rangeClosed(START, END).forEach(i -> process(DIRECTORY, Integer.toString(i)));
		IntStream.rangeClosed(START_PC, END).forEach(i -> process(DIRECTORY, i + "P"));
		IntStream.rangeClosed(START_PC, END).forEach(i -> process(DIRECTORY, i + "C"));
		System.exit(0);
	}

	static void process(String dir, String label) {
		try {
		BufferedImage image = null;
		Graphics2D g = null;
		int fontSize = 0;
		switch(mode) {
		case Mode.R_RECTANGLE:
			// Draw the filled Circle
			image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			g = image.createGraphics();
			g.setColor(Color.BLACK);
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, 10, 10);
			g.setColor(Color.WHITE);
			fontSize = FONT_SIZE_NORMAL;
			break;
		case Mode.CIRCLE:
			// Draw the filled Circle
			image = new BufferedImage(RADIUS, RADIUS, BufferedImage.TYPE_INT_RGB);
			g = image.createGraphics();
			g.setColor(Color.GRAY);
			g.fillOval(0, 0, RADIUS, RADIUS);
			g.setColor(Color.WHITE);
			fontSize = FONT_SIZE_BIG;
			break;
		case Mode.IMAGE:
			image = ImageIO.read(new File(dir + "/" + "coffeecup.png"));
			g = image.createGraphics();
			g.setColor(Color.WHITE);
			fontSize = FONT_SIZE_BIG;
			break;
		}

		// Draw the number
		Font textFont = new Font("SansSerif", Font.BOLD, fontSize);
		g.setFont(textFont);
		Rectangle2D lineMetrics = textFont.getStringBounds(label, g.getFontRenderContext());
		int x = (int)((image.getWidth() - lineMetrics.getWidth()) / 2);
		int y = (int)((image.getHeight() + lineMetrics.getHeight()) / 2);
		switch (mode) {
			case Mode.CIRCLE:
				y += 80; break;
			case Mode.IMAGE: 
				y += 60; break;
			default:
				// Nothing
				break;
		}
		System.out.println(STR."Drawing in \{g.getColor()} at \{x},\{y}");
		g.drawString(label, x, y);
		ImageIO.write(image, "png", 
			new File(STR."\{dir}/\{label}.png"));
		} catch (Exception ex) {
			throw new RuntimeException(STR."WTF: Failure \{ex}");
		}
	}
}
