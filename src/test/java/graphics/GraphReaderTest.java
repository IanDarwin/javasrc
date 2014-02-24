package graphics;

import org.junit.*;

import static org.junit.Assert.*;

public class GraphReaderTest {

	@Test
	public void readListOK() {
		String input = "12.34 56.78\n" +
			"3.14 3.14\n";
		Reader reader = new StringReader(input);
		List<Point2D> actual = GraphReader.read(reader);
		assertEqual(2, actual.size();
		assertEqual(new Point2D(12.34, 56.78), actual.get(0));
		assertEqual(new Point2D(3.14, 3.14), actual.get(0));
	}
}
