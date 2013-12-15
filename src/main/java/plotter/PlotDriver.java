package plotter;

/**
 * Main program, driver for Plotter class.
 * This is to simulate a larger graphics application such as GnuPlot.
 */
// BEGIN main
public class PlotDriver {

	/** Construct a Plotter driver, and try it out. */
	public static void main(String[] argv) {
		Plotter r ;
		if (argv.length != 1) {
			System.err.println("Usage: PlotDriver driverclass");
			return;
		}
		try {
			Class<?> c = Class.forName(argv[0]);
			Object o = c.newInstance();
			if (!(o instanceof Plotter))
				throw new ClassNotFoundException("Not instanceof Plotter");
			r = (Plotter)o;
		} catch (ClassNotFoundException e) {
			System.err.println("Sorry, class " + argv[0] +
					" not a plotter class");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		r.penDown();
		r.penColor(1);
		r.moveTo(200, 200);
		r.penColor(2);
		r.drawBox(123, 200);
		r.rmoveTo(10, 20);
		r.penColor(3);
		r.drawBox(123, 200);
		r.penUp();
		r.moveTo(300, 100);
		r.penDown();
		r.setFont("Helvetica", 14);
		r.drawString("Hello World");
		r.penColor(4);
		r.drawBox(10, 10);
	}
}
// END main
