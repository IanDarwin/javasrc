package gui.cardlayout;

import java.awt.*;
import java.awt.event.*;

/**
 * PrintPanel is the base for an open-ended series of classes
 * that implement printing of one type. We provide two examples 
 * to start: First prints a phone book, second prints labels, etc.
 * To add more, for example a Mail List/Form Letter Merge,
 * define a class for it below and insert in the "add" loop
 * in the main constructor.
 */
abstract class PrintPanel extends Panel {
	/** Returns the string to use in a Choice to display this panel */
	public abstract String getChoice();
	/** Print the data in the format for this type of printout */
	public abstract void doPrint();
}

/** PhonesPanel extends PrintPanel to provide the UI for printing
 * a user's phone book.
 */
class PhonesPanel extends PrintPanel {
	PhonesPanel() {
		super();
		setBackground(Color.red);
		setLayout(new FlowLayout());
		add(new Label("Tab markers at edge of page?"));
		add(new Checkbox());
		add(new Label("Each letter starts page?"));
		add(new Checkbox());
	}
	public String getChoice() {
		return "Phone Book";
	}
	public void doPrint() {
		// code here to print Phone book
	}
}


/** LabelsPanel extends PrintPanel to provide the UI for printing
 * name and address labels
 */
class LabelsPanel extends PrintPanel {
	LabelsPanel() {
		super();
		setBackground(Color.green);
		setLayout(new GridLayout(3,2));
		add(new Label("Left Offset:"));
		add(new TextField(5));
		add(new Label("Rows:"));
		add(new TextField(5));
		add(new Label("Cols:"));
		add(new TextField(5));
	}
	public String getChoice() {
		return "Labels";
	}
	public void doPrint() {
		// code here to print Labels
	}
}

/** CardLayDemo2 -- Prototype of a Print Dialog for JabaDex
 *
 * @author Ian F. Darwin
 */
public class CardLayDemo2 extends Frame {
	PrintPanel[] pps = new PrintPanel[2];
	private int runType = 0;
	private Choice runTypeChoice;

	private Panel tp, mainP, bp;	// top, middle, bottom.
	CardLayout cardlay;

	private Button printButton, 
		sampleButton, cancelButton;

	/** Construct a Print dialog. */
	CardLayDemo2(String title) {
		super(title);


		// Top panel (tp) has choices for labels/phonebook/etc.
		// and paper size.
		// Middle panel (mainP, managed by CardLayout) is details
		// for either Labels or Phonebook
		//	Shows either a PhonesPanel or a LabelsPanel or ...
		// Bottom panel (bp) has Print/Preview/Cancel buttons.
		tp = new Panel();
		tp.setLayout(new FlowLayout());

		mainP = new Panel();
		mainP.setLayout(cardlay = new CardLayout()); 

		tp.add(runTypeChoice = new Choice());
		runTypeChoice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				runType = runTypeChoice.getSelectedIndex();
				cardlay.show(mainP, pps[runType].getChoice());
			}
		});

		/* create one instance of each PrintPanel type. */
		pps[0] = new PhonesPanel();
		pps[1] = new LabelsPanel();

		/* Add each print type to the choice and to mainP */
		for (int i=0; i<pps.length; i++) {
			runTypeChoice.add(pps[i].getChoice());
			mainP.add(pps[i].getChoice(), pps[i]);
		}

		cardlay.show(mainP, pps[runType].getChoice());

		// Bottom has a Panel with push buttons
		bp = new Panel();
		bp.setLayout(new FlowLayout());
		bp.add(printButton = new Button("Print"));
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doPrint(true);
				setVisible(false);
				System.exit(0);
			}
		});
		bp.add(sampleButton = new Button("Print Sample"));
			sampleButton.setEnabled(false);
		bp.add(cancelButton = new Button("Cancel"));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Printing canceled");
				setVisible(false);
				System.exit(0);
			}
		});

		setLayout(new BorderLayout());
		add(tp, BorderLayout.NORTH);
		add(mainP, BorderLayout.CENTER);
		add(bp, BorderLayout.SOUTH);

		pack();

        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
        	// If windowClosing() does setVisible and dispose, 
			// then the close action completes
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
	}


	/** Print the current list.  */
	protected void doPrint(boolean toRealDevice) {

		// open a PrintStream to the printer device to file on disk.
		// PrintStream pf = ...

		// call the appropriate doPrint()
		// pps[runType].doPrint(pf);

		System.err.println("Print completed");
	}

	public static void main(String[] args) {

		// Generate some data...
		// ...

		// pop up the print dialog to print it
		(new CardLayDemo2("Print Tester")).setVisible(true);
	}
}
