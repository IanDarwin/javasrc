import java.io.*;
import java.lang.*;

/** Plotter class for testing higher-level software. */
public class PlotterDummy extends Plotter {

	/** Constructor: nothing to do */
	PlotterDummy() {
		super();
	}

	/** move to absolute location */
	boolean moveTo(int absx, int absy) {
		x = absx;
		y = absy;
		System.out.println("moveTo ["+x+","+y+"]");
		return true;
	}
	/** move to relative location */
	boolean rmoveTo(int incrx, int incry) {
		x += incrx;
		y += incry;
		System.out.println("rmoveTo ["+x+","+y+"]");
		return true;
	}
	boolean setdir(float deg) {
		dir = deg;
		return true;
	}
	void setPenState(boolean up) {
		penIsUp = up;
		System.out.println("Pen Up is ["+penIsUp+"]");
	}
	boolean penUp() {
		setPenState(true);
		return true;
	}
	boolean penDown() {
		setPenState(false);
		return true;
	}
	boolean penColor(int c) {
		penColor = c;
		System.out.println("PenColor is ["+penColor+"]");
		return true;
	}
}
