package structure;

import java.util.List;

import junit.framework.TestCase;

public class ValueListHandlerTest extends TestCase {

	ValueListHandler<Integer> fixture = new ValueListHandler<Integer>();

	protected void setUp() throws Exception {
		super.setUp();
		generateList(50);
	}

	/**
	 * @param howMany TODO
	 * 
	 */
	private void generateList(int howMany) {
		for (int i = 1; i <= howMany; i++) {
			fixture.add(i);
		}
	}

	public final void testAdd() {
		int i = fixture.getListSize();
		fixture.add(0);
		assertEquals(i + 1, fixture.getListSize());
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
}
