import java.awt.Container;
import javax.swing.*;

public class JTextAreaDemo {
	public static void main(String[] args) {
		JFrame jf = new JFrame("whoo");
		Container cp = jf.getContentPane();
		cp.add(new JTextArea(10, 10));
		jf.pack();
		jf.setVisible(true);
	}
}
