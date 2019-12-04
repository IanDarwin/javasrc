/**
 * This CODE FRAGMENT shows using a MediaTracker to ensure
 * that an Image has been loaded successfully, then obtaining
 * its Width and Height. The MediaTracker can track an arbitrary
 * number of Images; the "0" is an arbitrary number used to track
 * this particular image.
 */
Image im;
int imWidth, imHeight;
public void setImage(Image i) {
	im = i;
	MediaTracker mt = new MediaTracker(this);
	// use of "this" assumes we're in a Component subclass.
	mt.addImage(im, 0);
	try {
		mt.waitForID(0);
	} catch(InterruptedException e) {
		throw new IllegalArgumentException(
			"InterruptedException while loading Image");
	}
	if (mt.isErrorID(0)) {
		throw new IllegalArgumentException(
							"Couldn't load image");
	}
	imWidth  = im.getWidth(this);
	imHeight = im.getHeight(this);
}
