import java.awt.*;
import java.awt.image.*;
import java.io.*;

/** Try to display the contents of the QuickCam camera. */
public class QCam extends Component implements Runnable {
	/** frames/second */
	protected final int FPS = 10;

	/** The Camera's Device name (UNIX-specific) */
	protected static final String DEVNAME = "/dev/qcam0";
	/** A File object used to open a RandomAccessFile from the dev name */
	protected final File fileObj = new File(DEVNAME);
	/** The file to access the Camera */
	protected RandomAccessFile camFile;

	/** The data buffer */
        protected byte data[];

	/** A MemoryImageSource */
        protected MemoryImageSource source;

	/** An Image */
	Image im;

	/** The default image sizes from the QuickCam */
	protected final int WIDTH = 160, HEIGHT = 120, SIZE = WIDTH * HEIGHT;

	/** Construct a QCam object */
	public QCam() {
		super();
		// adapted from the MemoryAnimationSourceDemo by Garth Dickie): 
		data = new byte[SIZE];

		int value = 0;
		for (int i = 0; i < SIZE; i++) {
			data[i] = (byte)value;
		}

		// try {
			// camFile = new RandomAccessFile(fileObj, "r");
		// } catch (IOException e) {
			// System.err.println("open: " + e);
			// return;
		// }
		byte[] gray = new byte[64];
		for (byte i=0; i<64; i++)
			gray[i] = i;
		source = new MemoryImageSource(WIDTH, HEIGHT, 
			new IndexColorModel(8, 64, gray, gray, gray), data, 0, WIDTH);
		source.setAnimated(true);
		// im = createImage(source);

		// And start the animator Thread...
		new Thread(this).start();
	}

	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	public static void main(String[] args) {
		Frame f = new Frame(DEVNAME);
		f.add(new QCam());
		f.pack();
		f.setVisible(true);
	}

	/** The run method: sleep a bit, take another picture, ... */
	public void run() {
	    Thread me = Thread.currentThread( );
	    me.setPriority(Thread.MIN_PRIORITY);

	    while (true) {
		try {
		    Thread.sleep(2000 /* 60*FPS/1000 */);	//XXX
		} catch( InterruptedException e ) {
		    return;
		}

		System.out.println("tick");

		// Modify the values in the data array
		try {
			camFile = new RandomAccessFile(fileObj, "r");
			camFile.read(data);
			camFile.close();
		} catch(IOException e) {
			System.err.println("seek/read: " + e);
			return;
		}

		// Send the new data to the interested ImageConsumers
		source.newPixels(0, 0, WIDTH, HEIGHT);

		// reload the image.
		im = createImage(source);

		// Update the screen
		repaint();
	    }
	}

	public void paint(Graphics g) {
		if (im != null) {
			System.out.println("paint()");
			g.drawImage(im, 0, 0, this);
		} else {
			throw new IllegalStateException("paint(): im == null!");
		}
	}
}
