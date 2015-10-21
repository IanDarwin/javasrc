package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Reads an image, scales it to a height of THUMB_HEIGHT (150?), and writes it to a new file.
 * Works but misses rotation info, so some portrait images come out rotated 90 deg clockwise.
 * @author Ian Darwin
 */
public class ImageThumbnail {
	final static int THUMB_HEIGHT = 150;

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Usage: ImageThumbnail imageFile [...]");
		}
		int nThumb = 0;
		for (String fileName : args) {
			BufferedImage image = ImageIO.read(new File(fileName));
			Image thumb = image.getScaledInstance(-1, THUMB_HEIGHT, Image.SCALE_SMOOTH);
			BufferedImage bufferedThumb = 
				new BufferedImage(thumb.getWidth(null), thumb.getHeight(null), BufferedImage.TYPE_INT_RGB);
			bufferedThumb.getGraphics().drawImage(thumb, 0, 0, null);
			File outFile = new File(String.format("thumbnail%d.png", ++nThumb));
			ImageIO.write(bufferedThumb, "png", outFile);
			System.out.println("Thumbnail of " + fileName + " saved to " + outFile.getAbsolutePath());
		}
	}
}
