package structure;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ValueListHandlerTest {

	ValueListHandler<Integer> fixture =
		new ValueListHandler<Integer>(generateList(50));

	/**
	 * Make up the fake data.
	 */
	private List<Integer> generateList(int howMany) {
		List<Integer> t = new ArrayList<Integer>();
		for (int i = 1; i <= howMany; i++) {
			t.add(i);
		}
		return t;
	}

	@Test
	public final void testNextPage() {
		List<Integer> page = fixture.nextPage();
		assertEquals(10, page.size());
		assertEquals(Integer.valueOf(3), page.get(3 - 1)); // -1 cuz List starts @0
	}

	@Test
	public final void testPrevPage() {
		fixture.nextPage();	// 1-10
		fixture.nextPage(); // 11-20
		fixture.nextPage(); // 21-30
		List<Integer> page = fixture.prevPage();
		assertEquals(10, page.size());
		assertEquals(Integer.valueOf(13), page.get(3 - 1)); // -1 cuz List starts @0
	}

	@Test
	public final void testUnderflow() {
		fixture.nextPage();
		fixture.prevPage();
		fixture.prevPage();
		fixture.prevPage();
	}

	@Test
	public final void testOverflow() {
		fixture.nextPage();
		fixture.nextPage();
		fixture.nextPage();
		fixture.nextPage();
		fixture.nextPage();
		fixture.nextPage();
		fixture.nextPage();
		fixture.nextPage();
	}
	
	@Test
	public void testEvenPageSize() {
		fixture.setPageSize(20);
		assertEquals(20, fixture.nextPage().size());
		assertEquals(20, fixture.nextPage().size());
		assertEquals(10, fixture.nextPage().size());
	}
	
	@Test
	public void testOddPageSize() {
		fixture.setPageSize(19);
		assertEquals(19, fixture.nextPage().size());
		assertEquals(19, fixture.nextPage().size());
		assertEquals(12, fixture.nextPage().size());
	}
}
