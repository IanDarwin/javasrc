import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.regex.*;

/* Like REDemo but shows the groups in a TextField
 */
public class REDemo2 extends REDemo {

	JTextArea log;
	
	/** "main program" method - construct and show */
	public static void main(String[] av) {
		JFrame f = new JFrame("REDemo2");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		REDemo2 comp = new REDemo2();
		Container cp = f.getContentPane();
		cp.add(comp, BorderLayout.NORTH);
		cp.add(comp.log, BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
	}

	REDemo2() {
		super();
		log = new JTextArea(10,40);
		add(log);
	}

	protected boolean tryMatch() {
		log.setText("");
		if (!super.tryMatch())
			return false;
		for (int i=0; i <= matcher.groupCount(); i++) {
			log.append(i + ": " + matcher.group(i) + "\n");
		}
		return true;
	}
}
