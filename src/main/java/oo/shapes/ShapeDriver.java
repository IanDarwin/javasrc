package oo.shapes;

import java.util.Collection;
import java.util.Iterator;

// BEGIN main
/** Part of a main program using Shape objects */
public class ShapeDriver {

	Collection<Shape> allShapes;	// created in a Constructor, not shown

	/** Iterate over all the Shapes, getting their areas */
	public double totalAreas() {
		double total = 0.0;
		for (Shape s : allShapes) {
			total += s.computeArea();
		}
		return total;
	}
}
// END main
