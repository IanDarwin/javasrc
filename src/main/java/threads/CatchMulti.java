import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CatchMulti {
	public static void main(String[] args) {
		try {
			// Create the GUI, will implicitly create and start a GUI thread
			new ThrowAWT().setVisible(true); 
			// Wait a while.
			Thread.sleep(1000 * 20);
			// Create and start a thread that gives 
			// an ArrayIndexOutOfBoundsException
			new ThrowIndex().start();
		} catch (Exception ex) {
			System.out.println("Caught exception: " + ex);
			System.exit(1);
		}
	}
}

class ThrowAWT extends Frame {
	ThrowAWT() {
		Button b;
		add(b = new Button("Throw"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Deliberately throw a null pointer exception
				Random r = null;
				int i = r.nextInt();
			}
		});
		setBounds(60, 60, 60, 60);	// x, y, width, height
	}
}

class ThrowIndex extends Thread {
	public void run() {
		int[] data = new int[10];
		for (int i=1; i<=10; i++)
			data[i] = i;
	}
}
