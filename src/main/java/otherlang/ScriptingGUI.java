package otherlang;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/** Run a script from a GUI.
 * Based on a version of ScriptEnginesDemo that
 * I did for a customer, but not using any of the
 * code that I wrote for them.
 * If running under Eclipse, add lib/*engine.jar to classpath!
 * @author ian
 */
public class ScriptingGUI extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new ScriptingGUI().setVisible(true);
	}

	private ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

	private class EngineChoice {
		String name;
		ScriptEngineFactory factory;
		public EngineChoice(String languageName, ScriptEngineFactory factory) {
			this.name = languageName;
			this.factory = factory;
		}
		@Override
		public String toString() {
			return name;
		}
	}
	
	/** Set up the scripting engine list
	 * and the GUI.
	 */
	ScriptingGUI() {
		super("ScriptingGUI");
		
		// Get list of supported languages
		List<ScriptEngineFactory> engineFactories = 
			scriptEngineManager.getEngineFactories();
		final JComboBox engineChoiceBox = new JComboBox();
		for (ScriptEngineFactory f : engineFactories) {
			EngineChoice ec = new EngineChoice(f.getLanguageName(), f);
			engineChoiceBox.addItem(ec);
		}
		
		setLayout(new BorderLayout());
		JPanel top = new JPanel();
		top.add(engineChoiceBox);
		JPanel bot = new JPanel();
		JButton runButton = new JButton("Run");
		bot.add(runButton);
		
		final JTextArea area = new JTextArea(5,50);
		area.setBorder(BorderFactory.createTitledBorder("Enter Script:"));
		
		runButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					EngineChoice choice = (EngineChoice)engineChoiceBox.getSelectedItem();
					ScriptEngineFactory engineFactory = choice.factory;
					ScriptEngine engine = engineFactory.getScriptEngine();	
					String script = area.getText();
					System.out.println(engineFactory.getLanguageName());
					engine.eval(script);
				} catch (Throwable ex) {
					JOptionPane.showMessageDialog(ScriptingGUI.this, "Script failed, see log: " + ex);
					ex.printStackTrace();
				}
			}			
		});
		
		JButton quitButton = new JButton("Exit");
		bot.add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);		
			}			
		});
		
		add(top, BorderLayout.NORTH);
		
		add(area, BorderLayout.CENTER);

		add(bot, BorderLayout.SOUTH);

		pack();
	}
}
