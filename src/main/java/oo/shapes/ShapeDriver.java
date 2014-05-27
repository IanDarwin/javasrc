package oo.shapes;

import java.util.ArrayList;
import java.util.Collection;

// BEGIN main
/** Part of a main program using Shape objects */
public class ShapeDriver {

	Collection<Shape> allShapes;	// created in a Constructor, not shown

	/** Iterate over all the Shapes, getting their areas;
	 * this cannot use the Java 8 Collection.forEach because the
	 * variable total would have to be final, which would defeat the purpose :-) 
	 */
	public double totalAreas() {
		double total = 0.0;
		for (Shape s : allShapes) {
			total += s.computeArea();
		}
		return total;
	}
	// END main
	ShapeDriver() {
		allShapes = new ArrayList<>();
		allShapes.add(new Circle());
		allShapes.add(new Rectangle());
	}
	
	public static void main(String[] args) {
		System.out.println(new ShapeDriver().totalAreas());
	}
}
