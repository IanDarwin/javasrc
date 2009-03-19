package otherlang;

import java.awt.BorderLayout;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/** Run a script from a GUI.
 * Based on a version of ScriptEnginesDemo that
 * I did for a customer, but not using any of the
 * code that I wrote for them.
 * @author ian
 */
public class ScriptingGUI extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new ScriptingGUI().setVisible(true);
	}

	private ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

	/** Set up the scripting engine list
	 * and the GUI.
	 */
	ScriptingGUI() {
		super("ScriptingGUI");
		
		// Get list of supported languages
		List<ScriptEngineFactory> engineFactories = 
			scriptEngineManager.getEngineFactories();
		JComboBox box = new JComboBox(new Vector<ScriptEngineFactory>(engineFactories));
		
		setLayout(new BorderLayout());
		JPanel top = new JPanel();
		top.add(box);
		JPanel bot = new JPanel();
		bot.add(new JButton("Run"));
		
		add(top, BorderLayout.NORTH);
		
		JTextArea area = new JTextArea(5,50);
		area.setBorder(BorderFactory.createTitledBorder("Enter Script:"));
		add(area, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);

		pack();
	}
}
