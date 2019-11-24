package evals;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

class BorderPanel extends Panel {
	/** Paint -- just draw a tiny border */
	public void paint(Graphics g) {
		g.setColor(Color.red);
		Dimension d = getSize();
		g.draw3DRect(0, 0, d.width-1, d.height-1, true);
	}
}

/**
 * Daily Evaluation Applet for Learning Tree Int'l.
 * @Author Ian Darwin, http://www.darwinsys.com/
 */
public class DailyEval extends Applet {
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
	 * Gets the applet information.
	 * @return java.lang.String
	 */
	public String getAppletInfo() {
		return "DailyEval -- Copyright Ian F. Darwin";
	}

	/**
	 * The Applet init method.
	 */
	public void init() {
		super.init();
		setName("DailyEval");
		setLayout(new GridLayout(0,1));
		setBackground(Color.yellow);

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
		add(p);

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
		add(Label2);

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
		add(p);

		Label21 = new Label("Instructor");
		Label21.setFont(midFont);
		add(Label21);

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
		add(p);

		Label21 = new Label("Pace");
		Label21.setFont(midFont);
		add(Label21);

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
		add(p);

		// Panel for collection of large textfields/areas
		improveLabel = new Label("What improvements would you suggest?");
		improveLabel.setFont(midFont);
		add(improveLabel);
		improveTF = new TextField();
		improveTF.setName("improveTF");
		improveTF.setBackground(Color.white);
		add(improveTF);

		bestpartLabel = new Label("What was the best part of today's class?");
		bestpartLabel.setFont(midFont);
		bestpartTF = new TextField();
		bestpartTF.setName("bestpartTF");
		bestpartTF.setBackground(Color.white);
		add(bestpartLabel);
		add(bestpartTF);

		commentsLabel = new Label(
			"What suggestions would you like to make?");
		commentsLabel.setFont(midFont);
		add(commentsLabel);
		commentsTA = new TextArea(4, 70);
		commentsTA.setName("commentsTA");
		commentsTA.setBackground(Color.white);
		add(commentsTA);

		p = new Panel();
		Button1 = new Button("Send Evaluation");
		Button1.setName("Button1");
		p.add(Button1);
		add(p);
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendit();
			}
		});
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
			url = new URL(getCodeBase(), "DailyEvalThanks.html");
		} catch(MalformedURLException rsi) {
			showStatus("You lose: "+rsi);
		}
		getAppletContext().showDocument(url);
	}
}
