import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.regex.*;

/** Standalone Swing GUI application for demonstrating REs.
 * <br/>
 * TODO: 
 * Show the entire match, and $1 and up as captures that matched.
 * @author	Ian Darwin, http://www.darwinsys.com/
 * @version #Id$
 */
public class REDemo extends JPanel {
	protected Pattern pattern;
	protected Matcher matcher;
	protected JTextField patternTF, stringTF;
	protected JCheckBox compiledOK;
	protected JRadioButton match, find, findAll;
	protected JTextField matchesTF;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		JFrame f = new JFrame("REDemo");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		REDemo comp = new REDemo();
		f.setContentPane(comp);
		f.pack();
		f.setLocation(200, 200);
		f.setVisible(true);
	}

	/** Construct the REDemo object including its GUI */
	public REDemo() {
		super();

		JPanel top = new JPanel();
		top.add(new JLabel("Pattern:", JLabel.RIGHT));
		patternTF = new JTextField(20);
		patternTF.getDocument().addDocumentListener(new PatternListener());
		top.add(patternTF);
		top.add(new JLabel("Syntax OK?"));
		compiledOK = new JCheckBox();
		top.add(compiledOK);

		ChangeListener cl = new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				tryMatch();
			}
		};
		JPanel switchPane = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		match = new JRadioButton("Match");
		match.setSelected(true);
		match.addChangeListener(cl);
		bg.add(match);
		switchPane.add(match);
		find = new JRadioButton("Find");
		find.addChangeListener(cl);
		bg.add(find);
		switchPane.add(find);
		findAll = new JRadioButton("Find All");
		findAll.addChangeListener(cl);
		bg.add(findAll);
		switchPane.add(findAll);

		JPanel strPane = new JPanel();
		strPane.add(new JLabel("String:", JLabel.RIGHT));
		stringTF = new JTextField(20);
		stringTF.getDocument().addDocumentListener(new StringListener());
		strPane.add(stringTF);
		strPane.add(new JLabel("Matches:"));
		matchesTF = new JTextField(3);
		strPane.add(matchesTF);

		setLayout(new GridLayout(0, 1, 5, 5));
		add(top);
		add(strPane);
		add(switchPane);
	}

	protected void setMatches(boolean b) {
		if (b)
			matchesTF.setText("Yes");
		else
			matchesTF.setText("No");
	}

	protected void setMatches(int n) {
		matchesTF.setText(Integer.toString(n));
	}

	protected void tryCompile() {
		pattern = null;
		try {
			pattern = Pattern.compile(patternTF.getText());
			matcher = pattern.matcher("");
			compiledOK.setSelected(true);
		} catch (PatternSyntaxException ex) {
			compiledOK.setSelected(false);
		}
	}

	protected boolean tryMatch() {
		if (pattern == null)
			return false;
		matcher.reset(stringTF.getText());
		if (match.isSelected() && matcher.matches()) {
			setMatches(true);
			return true;
		}
		if (find.isSelected() && matcher.find()) {
			setMatches(true);
			return true;
		}
		if (findAll.isSelected()) {
			int i = 0;
			while (matcher.find()) {
				++i;
			}
			if (i > 0) {
				setMatches(i);
				return true;
			}
		}
		setMatches(false);
		return false;
	}

	/** Any change to the pattern tries to compile the result. */
	class PatternListener implements DocumentListener {

		public void changedUpdate(DocumentEvent ev) {
			tryCompile();
		}

		public void insertUpdate(DocumentEvent ev) {
			tryCompile();
		}

		public void removeUpdate(DocumentEvent ev) {
			tryCompile();
		}
	}

	/** Any change to the input string tries to match the result */
	class StringListener implements DocumentListener {


		public void changedUpdate(DocumentEvent ev) {
			tryMatch();
		}

		public void insertUpdate(DocumentEvent ev) {
			tryMatch();
		}

		public void removeUpdate(DocumentEvent ev) {
			tryMatch();
		}
	}
}
