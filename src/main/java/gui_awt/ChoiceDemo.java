package gui_awt;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/** Demonstrate use of Choice; also Font, action(), Event, ... */
public class ChoiceDemo extends Applet {
	Button	b1;
	Choice	cl;
	Label	myLabel;

	/** init: set a font, initialize UI components. */
	public void init() {
		setLayout(new FlowLayout());

		String pSize = getParameter("fontsize"); 
		if (pSize == null)
			pSize = "12";
		// System.out.println("Fontsize is " + pSize);
		Font f = new Font("Helvetica", Font.PLAIN, Integer.parseInt(pSize));
		setFont(f);
   
		/* Build the UI */
		add(cl = new Choice()) ;
		cl.setFont(f);	// ignored?
		cl.addItem("Whipping Cream");
		cl.addItem("Icing Sugar");
		cl.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				Apply();
			}
		});
		add(b1 = new Button("Apply"));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Apply();
			}
		});
		myLabel  = new Label("Please Choose Something", Label.CENTER);
		add(myLabel);
	}

	/** Return information about this applet. */
	public String getAppletInfo() {
		return "ChoiceDemo Applet, Version 0, Copyright Learning Tree International";
	}

	/** Return list of allowable parameters. */
	public String[][] getParameterInfo() {
		String param_info[][] = {
			{"fontsize",    "10-20",    "Size of font"},
		};
		return param_info;
	}

	private void Apply() {
		String item = cl.getSelectedItem();
		myLabel.setText(item);
	}
}
