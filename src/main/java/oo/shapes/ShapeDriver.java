package oo.shapes;

import java.util.Collection;

// BEGIN main
/** Part of a main program using Shape objects */
public class ShapeDriver {

	Collection<Shape> allShapes;	// created in a Constructor, not shown

	/** Iterate over all the Shapes, getting their areas */
	public double totalAreas() {
		double total = 0.0;
		allShapes.forEach(s ->
			total += s.computeArea());
		return total;
	}
}
// END main
