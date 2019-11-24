package graphics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;

/** MemImage is an in-memory icon showing a Color gradient. */
public class MemImage extends Component {

	private static final long serialVersionUID = 5051861823329422524L;

	/** Demo main program, showing two ways to use it.
	 * Create a small MemImage and set it as this Frame's iconImage.
	 * Also display a larger version of the same image in the Frame.
	 */
	public static void main(String[] av) {
		Frame f = new Frame("MemImage.java");
		f.add(new MemImage());
		f.setIconImage(new MemImage(16,16).getImage());
		f.pack();
		f.setVisible(true);
	}

	/** The image */
	private Image img;
	/** The image width */
	private int w;
	/** The image height */
	private int h;

	/** Construct a MemImage with a default size */
	public MemImage() {
		this(100,100);
	}

	/** Construct a MemImage with a specified width and height */
	public MemImage(int w, int h) {
		this.w = w;
        this.h = h;
        int pix[] = new int[w * h];
        int index = 0;
        for (int y = 0; y < h; y++) {
            int red = (y * 255) / (h - 1);
            for (int x = 0; x < w; x++) {
                int blue = (x * 255) / (w - 1);
                pix[index++] = (255 << 24) | (red << 16) | blue;
            }
        }
        img = createImage(new MemoryImageSource(w, h, pix, 0, w));
		setSize(getPreferredSize());
	}

	/** Getter for the Image */
	public Image getImage() {
		return img;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(w, h);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, getSize().width, getSize().height, this);
	}
}
