package datetime;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/** Bean to display a month calendar in a JPanel. 
 * Only works for the Western calendar. 
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class Cal extends JPanel {
	/** The currently-interesting year (not modulo 1900!) */
	protected int yy;
	/** Currently-interesting month and day */
	protected int mm, dd;
	/** The buttons to be displayed */
	protected JButton labs[][];
	/** The number of day squares to leave blank at the start of this month */
	protected int leadGap = 0;
	/** A Calendar object used throughout */
	Calendar calendar = new GregorianCalendar();
	/** Today's year */
	protected final int thisYear = calendar.get(Calendar.YEAR);
	/** Today's month */
	protected final int thisMonth = calendar.get(Calendar.MONTH);
	/** One of the buttons. We just keep its reference for getBackground().*/
	private JButton b0;
	/** The month choice */
	private JComboBox monthChoice;
	/** The year choice */
	private JComboBox yearChoice;

	/** Construct a Cal, starting with today.
	 */
	Cal() {
		super();
		setYYMMDD(calendar.get(Calendar.YEAR),
			calendar.get(Calendar.MONTH),
			calendar.get(Calendar.DAY_OF_MONTH));
		buildGUI();
		recompute();
	}

	/** Construct a Cal, given the leading days and the total days
	 * @exception	IllegalArgumentException	If year out of range
	 */
	Cal(int year, int month, int today) {
		super();
		setYYMMDD(year, month, today);
		buildGUI();
		recompute();
	}

	private void setYYMMDD(int year, int month, int today) {
		yy = year;
		mm = month;
		dd = today;
	}

	String[] months = {
		"January", "February", "March", "April",
		"May", "June", "July", "August",
		"September", "October", "November", "December"
	};

	/** Build the GUI. Assumes that setYYMMDD has been called. */
	private void buildGUI() {
		getAccessibleContext().setAccessibleDescription(
			"Calendar not accessible yet. Sorry!");
		setBorder(BorderFactory.createEtchedBorder());

		setLayout(new BorderLayout());

		JPanel tp = new JPanel();
		tp.add(monthChoice = new JComboBox());
		for (int i=0; i<months.length; i++)
			monthChoice.addItem(months[i]);
		monthChoice.setSelectedItem(months[mm]);
		monthChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int i = monthChoice.getSelectedIndex();
				if (i>=0) {
					mm = i;
					// System.out.println("Month=" + mm);
					recompute();
				}
			}
		});
		monthChoice.getAccessibleContext().setAccessibleName("Months");
		monthChoice.getAccessibleContext().setAccessibleDescription("Choose a month of the year");

		tp.add(yearChoice = new JComboBox());
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
					recompute();
				}
			}
		});
		add(BorderLayout.CENTER, tp);

		JPanel bp = new JPanel();
		bp.setLayout(new GridLayout(7,7));
		labs = new JButton[6][7];	// first row is days

		bp.add(b0 = new JButton("S"));
		bp.add(new JButton("M"));
		bp.add(new JButton("T"));
		bp.add(new JButton("W"));
		bp.add(new JButton("R"));
		bp.add(new JButton("F"));
		bp.add(new JButton("S"));

		ActionListener dateSetter = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				if (!num.equals("")) {
					// set the current day highlighted
					setDayActive(Integer.parseInt(num));
					// When this becomes a Bean, you can
					// fire some kind of DateChanged event here.
					// Also, build a similar daySetter for day-of-week btns.
				}
			}
		};

		// Construct all the buttons, and add them.
		for (int i=0; i<6; i++)
			for (int j=0; j<7; j++) {
				bp.add(labs[i][j] = new JButton(""));
				labs[i][j].addActionListener(dateSetter);
			}

		add(BorderLayout.SOUTH, bp);
	}

	/** Compute which days to put where, in the Cal panel */
	protected void recompute() {
		// System.out.println("Cal::recompute: " + yy + ":" + mm + ":" + dd);
		if (mm < 0 || mm > 11)
			throw new IllegalArgumentException("Month " + mm + " bad, must be 0-11");
		clearDayActive();
		calendar = new GregorianCalendar(yy, mm, dd);

		// Compute how much to leave before the first.
		// getDay() returns 0 for Sunday, which is just right.
		leadGap = new GregorianCalendar(yy, mm, 1).get(Calendar.DAY_OF_WEEK)-1;
		// System.out.println("leadGap = " + leadGap);

		int daysInMonth = CalUtils.getDaysInMonth(mm);
		if (isLeap(calendar.get(Calendar.YEAR)) && mm > 1)
			++daysInMonth;

		// Blank out the labels before 1st day of month
		for (int i = 0; i < leadGap; i++) {
			labs[0][i].setText("");
		}

		// Fill in numbers for the day of month.
		for (int i = 1; i <= daysInMonth; i++) {
			JButton b = labs[(leadGap+i-1)/7][(leadGap+i-1)%7];
			b.setText(Integer.toString(i));
		}

		// 7 days/week * up to 6 rows
		for (int i = leadGap+1+daysInMonth; i < 6*7; i++) {
			labs[(i)/7][(i)%7].setText("");
		}

		// Shade current day, only if current month
		if (thisYear == yy && mm == thisMonth)
			setDayActive(dd);		// shade the box for today

		// Say we need to be drawn on the screen
		repaint();
	}

	/**
	 * isLeap() returns true if the given year is a Leap Year.
	 *
	 * "a year is a leap year if it is divisible by 4
	 * but not by 100, except that years divisible by 400
	 * *are* leap years." 
	 *	-- Kernighan & Ritchie, _The C Programming Language_, p 37.
	 */
	public boolean isLeap(int year) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return true;
		return false;
	}

	/** Set the year, month, and day */
	public void setDate(int yy, int mm, int dd) {
		// System.out.println("Cal::setDate");
		this.yy = yy;
		this.mm = mm;		// starts at 0, like Date
		this.dd = dd;
		recompute();
	}

	/** Unset any previously highlighted day */
	private void clearDayActive() {
		JButton b;

		// First un-shade the previously-selected square, if any
		if (activeDay > 0) {
			b = labs[(leadGap+activeDay-1)/7][(leadGap+activeDay-1)%7];
			b.setBackground(b0.getBackground());
			b.repaint();
			activeDay = -1;
		}
	}

	private int activeDay = -1;

	/** Set just the day, on the current month */
	public void setDayActive(int newDay) {

		clearDayActive();

		// Set the new one
		if (newDay <= 0)
			dd = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
		else
			dd = newDay;
		// Now shade the correct square
		Component square = labs[(leadGap+newDay-1)/7][(leadGap+newDay-1)%7];
		square.setBackground(Color.red);
		square.repaint();
		activeDay = newDay;
	}

	/** For testing, a main program */
	public static void main(String[] av) {
		JFrame f = new JFrame("Cal");
		Container c = f.getContentPane();
		c.setLayout(new FlowLayout());

		// for this test driver, hardcode 1995/02/10.
		c.add(new Cal(1995, 2-1, 10));

		// and beside it, the current month.
		c.add(new Cal());

		f.pack();
		f.setVisible(true);
	}
}
