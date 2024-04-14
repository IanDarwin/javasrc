package evals;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Daily Evaluation for students in class
 * @Author Ian Darwin, https://darwinsys.com/
 */
public class DailyEval {
	public static void main(String[] args) {
		new DailyEval();
	}

	private Button Button1 = null;
	private CheckboxGroup instrCBG;
	private CheckboxGroup crsCBG;
	private CheckboxGroup paceCBG;
	private Checkbox Checkbox1 = null;
	private Checkbox Checkbox11 = null;
	private Checkbox Checkbox2 = null;
	private Checkbox Checkbox21 = null;
	private Checkbox Checkbox3 = null;
	private Checkbox Checkbox31 = null;
	private Checkbox Checkbox4 = null;
	private Checkbox Checkbox41 = null;
	private Checkbox Checkbox5 = null;
	private Checkbox Checkbox51 = null;
	private Label Label2 = null;
	private Label Label21 = null;
	private Label bestpartLabel = null;
	private Label improveLabel = null;
	private Label commentsLabel = null;
	private Label yournameLabel = null;
	private TextArea commentsTA = null;
	private TextField yournameTF = null;
	private TextField bestpartTF = null;
	private TextField improveTF = null;
	private Font bigFont, midFont, smlFont;

	/**
	 * Constructor
	 */
	public DailyEval() {
		super();
		JFrame jf = new JFrame();
		jf.setName("DailyEval");
		jf.setLayout(new GridLayout(0,1));
		jf.setBackground(Color.yellow);

		bigFont = new Font("helvetica", 1, 24);
		midFont = new Font("helvetica", 1, 18);
		smlFont = new Font("helvetica", 1, 14);

		Panel p = new Panel();
		yournameLabel = new Label("Your name:", Label.RIGHT);
		yournameLabel.setFont(midFont);
		p.add(yournameLabel);
		yournameTF = new TextField(20);
		yournameTF.setName("yournameTF");
		yournameTF.setBackground(Color.white);
		p.add(yournameTF);
		jf.add(p);

		// Checkboxen for the Instructor Grade panel
		Checkbox1 = new Checkbox();
		Checkbox1.setName("4");
		Checkbox1.setLabel("4-Good");
		Checkbox2 = new Checkbox();
		Checkbox2.setName("3");
		Checkbox2.setLabel("3-OK");
		Checkbox3 = new Checkbox();
		Checkbox3.setName("2");
		Checkbox3.setLabel("2-Fair");
		Checkbox4 = new Checkbox();
		Checkbox4.setName("1");
		Checkbox4.setLabel("1-Poor");
		Checkbox5 = new Checkbox();
		Checkbox5.setName("0");
		Checkbox5.setLabel("0-Unacceptable");

		// Checkboxen for the Course Grade panel
		Checkbox11 = new Checkbox();
		Checkbox11.setName("4");
		Checkbox11.setLabel("4-Good");
		Checkbox21 = new Checkbox();
		Checkbox21.setName("3");
		Checkbox21.setLabel("3-OK");
		Checkbox31 = new Checkbox();
		Checkbox31.setName("2");
		Checkbox31.setLabel("2-Fair");
		Checkbox41 = new Checkbox();
		Checkbox41.setName("1");
		Checkbox41.setLabel("1-Poor");
		Checkbox51 = new Checkbox();
		Checkbox51.setName("0");
		Checkbox51.setLabel("0-Unacceptable");

		Label2 = new Label("Course");
		Label2.setFont(midFont);
		jf.add(Label2);

		// Panel for the Course Grade
		p = new Panel();
		p.setName("crsPanel");
		crsCBG = new CheckboxGroup();
		p.add(Checkbox1);
		Checkbox1.setCheckboxGroup(crsCBG);
		p.add(Checkbox2);
		Checkbox2.setCheckboxGroup(crsCBG);
		p.add(Checkbox3);
		Checkbox3.setCheckboxGroup(crsCBG);
		p.add(Checkbox4);
		Checkbox4.setCheckboxGroup(crsCBG);
		p.add(Checkbox5);
		Checkbox5.setCheckboxGroup(crsCBG);
		crsCBG.setSelectedCheckbox(Checkbox1);
		jf.add(p);

		Label21 = new Label("Instructor");
		Label21.setFont(midFont);
		jf.add(Label21);

		// Panel for the Instructor grade
		p = new Panel();
		p.setName("instrPanel");
		instrCBG = new CheckboxGroup();
		p.add(Checkbox11);
		Checkbox11.setCheckboxGroup(instrCBG);
		p.add(Checkbox21);
		Checkbox21.setCheckboxGroup(instrCBG);
		p.add(Checkbox31);
		Checkbox31.setCheckboxGroup(instrCBG);
		p.add(Checkbox41);
		Checkbox41.setCheckboxGroup(instrCBG);
		p.add(Checkbox51);
		Checkbox51.setCheckboxGroup(instrCBG);
		instrCBG.setSelectedCheckbox(Checkbox11);
		jf.add(p);

		Label21 = new Label("Pace");
		Label21.setFont(midFont);
		jf.add(Label21);

		// Panel for the Pace grade
		p = new Panel();
		p.setName("pacePanel");
		paceCBG = new CheckboxGroup();
		Checkbox cb;
		p.add(cb = new Checkbox("OK", paceCBG, true));
		cb.setName("K");
		p.add(cb = new Checkbox("Too fast", paceCBG, false));
		cb.setName("F");
		p.add(cb = new Checkbox("Too slow", paceCBG, false));
		cb.setName("S");
		jf.add(p);

		// Panel for collection of large textfields/areas
		improveLabel = new Label("What improvements would you suggest?");
		improveLabel.setFont(midFont);
		jf.add(improveLabel);
		improveTF = new TextField();
		improveTF.setName("improveTF");
		improveTF.setBackground(Color.white);
		jf.add(improveTF);

		bestpartLabel = new Label("What was the best part of today's class?");
		bestpartLabel.setFont(midFont);
		bestpartTF = new TextField();
		bestpartTF.setName("bestpartTF");
		bestpartTF.setBackground(Color.white);
		jf.add(bestpartLabel);
		jf.add(bestpartTF);

		commentsLabel = new Label(
			"What suggestions would you like to make?");
		commentsLabel.setFont(midFont);
		jf.add(commentsLabel);
		commentsTA = new TextArea(4, 70);
		commentsTA.setName("commentsTA");
		commentsTA.setBackground(Color.white);
		jf.add(commentsTA);

		p = new Panel();
		Button1 = new Button("Send Evaluation");
		Button1.setName("Button1");
		p.add(Button1);
		jf.add(p);
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendit();
			}
		});
		jf.pack();
		jf.setVisible(true);
	}

	/** This handles all the action on the button */
	private void sendit() {
		System.err.println("-- doing action -- ");
		System.out.println("NAME: " + yournameTF.getText());
		System.out.println("INSTR: " +
			instrCBG.getSelectedCheckbox().getName());
		System.out.println("COURSE: " +
			crsCBG.getSelectedCheckbox().getName());
		System.out.println("PACE: " +
			paceCBG.getSelectedCheckbox().getName());
		System.out.println("IMPROVE: " + improveTF.getText());
		System.out.println("BESTPART: " + bestpartTF.getText());
		System.out.println("SUGGESTIONS: " + commentsTA.getText());
		URL url = null;
		try {
			url = URI.create("http://localhost:8080/dailyevals/DailyEvalThanks.html").toURL();
		} catch(MalformedURLException rsi) {
			showStatus("You lose: "+rsi);
			return;
		}
		showDocument(url);
	}
	void showStatus(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	void showDocument(URL url) {
		showStatus("Would now open " + url);
	}
}
