package structure;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ValueListHandlerTest extends TestCase {

	ValueListHandler<Integer> fixture;

	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ValueListHandler<Integer>(generateList(50));
	}

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

	public final void testNextPage() {
		List<Integer> page = fixture.nextPage();
		assertEquals(10, page.size());
		assertEquals(Integer.valueOf(3), page.get(3 - 1)); // -1 cuz List starts @0
	}

	public final void testPrevPage() {
		fixture.nextPage();	// 1-10
		fixture.nextPage(); // 11-20
		fixture.nextPage(); // 21-30
		List<Integer> page = fixture.prevPage();
		assertEquals(10, page.size());
		assertEquals(Integer.valueOf(13), page.get(3 - 1)); // -1 cuz List starts @0
	}

	public final void testUnderflow() {
		fixture.nextPage();
		fixture.prevPage();
		fixture.prevPage();
		fixture.prevPage();
	}

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
	
	public void testPageSize() {
		fixture.setPageSize(20);
		assertEquals(20, fixture.nextPage().size());
		assertEquals(20, fixture.nextPage().size());
		assertEquals(10, fixture.nextPage().size());
	}
}
