package xcolor;

import java.awt.Color;

import static org.junit.Assert.*;
import org.junit.Test;

/** Test for XColor */
public class XColorTest {

	protected String[] colors = {
		"PapayaWhip",
		"mint cream",
		"DarkSlateGrey",
		"light grey",
	};
	protected int[] rgb = {
		new Color( 255, 239, 213).getRGB(),		// "papayawhip"
		new Color( 245, 255, 250).getRGB(),		// "mint cream"
		new Color(  47,  79,  79).getRGB(),		// "darkslategray"
		new Color( 211, 211, 211).getRGB()		// "light gray"
	};

	@Test
	public void testColor() {
		int i = 0;
		for (String s : colors) {
			Color cc = new XColor(s);
			assertEquals("RGB Match", rgb[i++], cc.getRGB());
		}
	}
}
