package graphics;

import static org.junit.Assert.assertEquals;

import java.awt.geom.Point2D;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

public class GraphReaderTest {

	@Test
	public void readListOK() throws Exception {
		String input = "12.34 56.78\n" +
			"3.14 3.14\n";
		Reader reader = new StringReader(input);
		List<Point2D> actual = GraphReader.read(reader, "unit test input");
		assertEquals(2, actual.size());
		assertEquals(new Point2D.Double(12.34, 56.78), actual.get(0));
		assertEquals(new Point2D.Double(3.14, 3.14), actual.get(1));
	}
}
