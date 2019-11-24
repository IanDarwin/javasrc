package sched;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

import com.darwinsys.swingui.UtilGUI;

import java.util.*;

public class Cal extends JPanel implements DateSelectedListener {

	private static final long serialVersionUID = 1L;
	/** The top panel, for "go to date" */
	protected JPanel gotoPanel;
	/** The main view, showing day view, month view or year view */
	protected JTabbedPane mainPanel;
	/** The Day View */
	protected CalDay dayPane;
	/** The Month View */
	protected CalMonth monthPane;
	/** The Year View */
	protected JPanel yearPane;
	/** A Calendar object used throughout */
	final protected Calendar calendar = new GregorianCalendar();
	/** Today's year */
	protected final int thisYear = calendar.get(Calendar.YEAR);
	/** Today's month */
	protected final int thisMonth = calendar.get(Calendar.MONTH);
	/** Today's date */
	protected final int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
	/** A button to jump to Today from anyplace */
	protected JButton todayButton;
	/** The month choice */
	private JComboBox<String> monthChoice;
	/** The day choice */
	private JComboBox<String> dayChoice;
	/** The year choice */
	private JComboBox<String> yearChoice;
	protected int mm = thisMonth, yy = thisYear, dd = thisDay;
	final String[] months = {
		"January", "February", "March", "April",
		"May", "June", "July", "August",
		"September", "October", "November", "December"
	};
	/** The Event manager */
	protected CalEventMgr mgr;

	public Cal() {

		mgr = new CalEventMgr();
		mgr.addDateSelectedListener(this);

		setLayout(new BorderLayout());

		// TOP - the "go to date" panel
		add(BorderLayout.NORTH, gotoPanel = new JPanel());

		gotoPanel.add(todayButton = new JButton("Today"));
		todayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setYYMMDD(thisYear, thisMonth, thisDay);
			}
		});

		gotoPanel.add(monthChoice = new JComboBox<>());
		for (int i=0; i<months.length; i++)
			monthChoice.addItem(months[i]);
		monthChoice.setSelectedItem(months[thisMonth]);
		monthChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int i = monthChoice.getSelectedIndex();
				if (i>=0) {
					mm = i;
					// System.out.println("Month=" + mm);
					mgr.fireEvent(new DateSelectedEvent(Cal.this, yy,mm,dd));
				}
			}
		});
		monthChoice.getAccessibleContext().setAccessibleName("Months");
		monthChoice.getAccessibleContext().setAccessibleDescription("Choose a month of the year");

		gotoPanel.add(dayChoice = new JComboBox<>());
		for (int i=1; i<31; i++)
			dayChoice.addItem(""+i);
		dayChoice.setSelectedIndex(thisDay-1);
		dayChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int i = dayChoice.getSelectedIndex();
				if (i>=0) {
					dd = i;
					// System.out.println("day=" + dd);
					mgr.fireEvent(new DateSelectedEvent(Cal.this, yy,mm,dd));
				}
			}
		});
		dayChoice.getAccessibleContext().setAccessibleName("Days");
		dayChoice.getAccessibleContext().setAccessibleDescription("Choose day of month");

		gotoPanel.add(yearChoice = new JComboBox<String>());
		yearChoice.setEditable(true);
		for (int i=yy-5; i<yy+5; i++)
			yearChoice.addItem(Integer.toString(i));
		yearChoice.setSelectedItem(Integer.toString(yy));
		yearChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int i = yearChoice.getSelectedIndex();
				if (i>=0) {
					yy = Integer.parseInt(yearChoice.getSelectedItem().toString());
					// System.out.println("Year=" + yy);
					mgr.fireEvent(new DateSelectedEvent(Cal.this, yy,mm,dd));
				}
			}
		});

		// BOTTOM - the main display

		add(BorderLayout.CENTER, mainPanel = new JTabbedPane());

		mainPanel.setTabPlacement(javax.swing.SwingConstants.LEFT);

		// Day View
		mainPanel.addTab("Day", dayPane = new CalDay());
		mgr.addDateSelectedListener(dayPane);

		mainPanel.addTab("Week",
			new JLabel("Week View Not Written Yet", JLabel.CENTER));
		//mgr.addDateSelectedListener(weekPane);

		// Month View
		mainPanel.addTab("Month", monthPane = new CalMonth(mgr));
		mgr.addDateSelectedListener(monthPane);

		// Year View
		//mainPanel.addTab("Year", yearPane = new CalYear());
		//mgr.addDateSelectedListener(yearPane);
		mainPanel.addTab("Year",
			new JLabel("Year View Not Written Yet", JLabel.CENTER));

		mainPanel.setSelectedIndex(2);	// Month
	}

	/** The real "action" handler, called from other Event sources */
	public void dateSelected(DateSelectedEvent dse) {
		setYYMMDD(dse.yy, dse.mm, dse.dd);
	}

	/** Set our indicators to the given yymmdd */
	protected void setYYMMDD(int yy, int mm, int dd) {
		yearChoice.setSelectedItem(Integer.toString(yy));
		monthChoice.setSelectedIndex(mm);
		dayChoice.setSelectedItem(Integer.toString(dd));
	}
	
	/** For shorter printouts */
	public String toString() {
		return getClass().getName() + '@' + hashCode();
	}

	// JDMODULE CONFORMANCE

	/** Start a new file, prompting to save the old one first. */
	public void newFile(){ }

	/** Load new model from fn{ } if null, prompt for new fname */
	public void loadFile(String fn){ }

	/** Save the current model's data in fn. 
	 * If null, use current fname or prompt for a filename. */
	public void saveFile(String fn){
		System.out.println("Cal has nothing to save yet");
	}

	/** Export the file in a (portable?) format.
	 */
	public void exportFile() {
		System.out.println("Cal Export not implemented yet");
	}

	/** Ask the model if it has any unsaved changes, don't save otherwise */
	public boolean hasUnsavedChanges(){return false; }

	/** If the module wants AutoSave when the user enables it. */
	public boolean wantAutoSave(){return false; }

	/** Start the module's print routine */
	public void doPrint(){ 
		JOptionPane.showMessageDialog(null,
			"Can't print calendar yet", "TODO",
			JOptionPane.INFORMATION_MESSAGE);
	}

	/** Create a new item (usually by dialog?) */
	public void newItem(){ }

	/** Modify the current via a dialog */
	public void modItem(){ }

	/** Edit->Copy */
	public void editCopy(){ }

	/** Edit->Cut */
	public void editCut(){ }

	/** Edit->Paste */
	public void editPaste(){ }

	/** Edit->Delete */
	public void editDelete(){ }

	/** If can undo */
	public boolean hasUndoableChange(){return false; }

	/** Edit->Undo */
	public void undoChange(){ }

	/** Edit->ReDo */
	public void redoChange(){ }

	/** Synchronize with a PDA (tentative - functionality may go elsewhere) */
	public void doSynch(){ }

	/** For testing, a main program */
	public static void main(String[] av) {
		JFrame f = new JFrame("Cal");

		f.getContentPane().add(new Cal());

		f.pack();
		UtilGUI.centre(f);
		f.setVisible(true);
	}
}
