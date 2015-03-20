package plotter;

/** Plotter class for testing higher-level software. */
public class PlotterDummy extends Plotter {

	/** Constructor: nothing to do */
	PlotterDummy() {
		super();
	}

	/** move to absolute location */
	void moveTo(int absx, int absy) {
		curx = absx;
		cury = absy;
		System.out.println("moveTo ["+curx+","+cury+"]");
	}
	/** move to relative location */
	void rmoveTo(int incrx, int incry) {
		curx += incrx;
		cury += incry;
		System.out.println("rmoveTo ["+curx+","+cury+"]");
	}
	public void setFont(java.lang.String fName, int fSize) {
		System.out.println("set Font to " + fName);
	}

	public void drawString(java.lang.String s) {
		System.out.println("Draw the string \"" + s + "\"");
	}

	void setPenState(boolean up) {
		penUp = up;
		System.out.println("Pen Up is ["+penUp+"]");
	}
	void penUp() {
		setPenState(true);
	}
	void penDown() {
		setPenState(false);
	}
	void penColor(int c) {
		penColor = c;
		System.out.println("PenColor is ["+penColor+"]");
	}
}
