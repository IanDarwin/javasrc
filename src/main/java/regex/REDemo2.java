import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/* Like REDemo but shows the groups in a TextField
 */
public class REDemo2 extends REDemo {

	JTextArea logTextArea;
	
	/** "main program" method - construct and show */
	public static void main(String[] av) {
		JFrame f = new JFrame("REDemo2");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		REDemo2 comp = new REDemo2();
		Container cp = f.getContentPane();
		cp.add(comp, BorderLayout.NORTH);
		cp.add(comp.logTextArea, BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
	}

	REDemo2() {
		super();
		logTextArea = new JTextArea(10,40);
		add(logTextArea);
	}

	protected boolean tryMatch() {
		if (pattern == null) {
			return false;
		}
		logTextArea.setText("");
		if (!super.tryMatch()) {
			return false;
		}
		int n = matcher.groupCount();
		matcher.reset(stringTF.getText());
		if (match.isSelected() && matcher.matches()) {
			logTextArea.setText(matcher.group());
			return true;
		}
		if (find.isSelected() && matcher.find()) {
			logTextArea.setText(matcher.group());
			return true;
		}
		if (findAll.isSelected()) {
			int i;
			for (i = 0; i < n; i++) {
				matcher.find();
				logTextArea.append(i + ": " + matcher.group(i) + "\n");
			}
			if (i > 0) {
				return true;
			}
		}
		setMatches(false);
		return false;
	}
}
