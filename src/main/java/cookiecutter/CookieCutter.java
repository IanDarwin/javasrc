import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

/** CookieCutter - program to show, and let you winnow, your Cookie collection.
 */
public class CookieCutter {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Vector cookies = new CookieIO().read("cookies.txt");
		// Collections.sort(cookies);

		JFrame f = new JFrame("CookieCutter");
		Container cp = f.getContentPane();

		JList list = new JList();
		list.setListData(cookies);
		cp.add(new JScrollPane(list), BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
	}
}
