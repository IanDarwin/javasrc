package presentation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ValueListHandlerTest {

	ValueListHandler<Integer> target =
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
		List<Integer> page = target.nextPage();
		assertEquals(10, page.size());
		assertEquals(Integer.valueOf(3), page.get(3 - 1)); // -1 cuz List starts @0
	}

	@Test
	public final void testPrevPage() {
		target.nextPage();	// 1-10
		target.nextPage(); // 11-20
		target.nextPage(); // 21-30
		List<Integer> page = target.prevPage();
		assertEquals(10, page.size());
		assertEquals(Integer.valueOf(13), page.get(3 - 1)); // -1 cuz List starts @0
	}

	@Test
	public final void testUnderflow() {
		target.nextPage();
		target.prevPage();
		target.prevPage();
		target.prevPage();
	}

	@Test
	public final void testOverflow() {
		target.nextPage();
		target.nextPage();
		target.nextPage();
		target.nextPage();
		target.nextPage();
		target.nextPage();
		target.nextPage();
		target.nextPage();
	}
	
	@Test
	public void testEvenPageSize() {
		target.setPageSize(20);
		assertEquals(20, target.nextPage().size());
		assertEquals(20, target.nextPage().size());
		assertEquals(10, target.nextPage().size());
	}
	
	@Test
	public void testOddPageSize() {
		target.setPageSize(19);
		assertEquals(19, target.nextPage().size());
		assertEquals(19, target.nextPage().size());
		assertEquals(12, target.nextPage().size());
	}
}
