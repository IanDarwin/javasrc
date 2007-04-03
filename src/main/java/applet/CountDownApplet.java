package applet;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class CountDownApplet extends JApplet {

	private static final long serialVersionUID = -1409814015168654676L;
	private final JLabel statusLabel = new JLabel("----------");
	private int i = 0;

	@Override
	public void init() {
		add(statusLabel);
	}

	@Override
	public void start() {
		for (i = 5; i > 0; i--) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					System.out.println(i);
					statusLabel.setText("     " + Integer.toString(i));
					statusLabel.repaint();
				}
			});
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// CantHappen
			}

		}
		statusLabel.setText("Time's up!");

	}
}
