package oo.shapes;

import java.util.Collection;
import java.util.Iterator;

/** Part of a main program using Shape objects */
public class Main {

	Collection allShapes;	// created in a Constructor, not shown

	/** Iterate over all the Shapes, getting their areas */
	public double totalAreas() {
		Iterator it = allShapes.iterator();
		double total = 0.0;
		while (it.hasNext()) {
			Shape s = (Shape)it.next();
			total += s.computeArea();
		}
		return total;
	}
}
