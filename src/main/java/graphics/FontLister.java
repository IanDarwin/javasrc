import java.awt.*;

public class FontLister {

	public static void main(String av[]) {

		Toolkit t = Toolkit.getDefaultToolkit();

		String fl[] = t.getFontList();

		System.out.println("Number of Fonts = " + fl.length);

		for (int i = 0; i<fl.length; i++)
			System.out.println("Font " + i + " = " + fl[i]);
	}
}
