package plotter;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlotterTest {
	Plotter p;
	
	@Before
	public void setUp() throws Exception {
		p = new PlotterDummy();
	}
	
	@Test
	public void testInitial() {
		assertEquals("initial", new Point(0,0), p.getLocation());
	}
	
	@Test
	public void testMoveTo() {
		p.moveTo(123, 456);
		assertEquals("abs move", new Point(123, 456), p.getLocation());
	}

	@Test
	public void testRMoveTo() {
		p.moveTo(100, 100);
		p.rmoveTo(123, 456);
		assertEquals("abs move", new Point(223, 556), p.getLocation());
	}
}
