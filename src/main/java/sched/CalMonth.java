package sched;

// $Id$

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/** Bean to display a month calendar in a JPanel. 
 * Only works for the Western calendar. 
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class CalMonth extends JPanel implements DateSelectedListener {
	/** The CalEventMgr lets us send our selected date to other parts
	 * of the Cal collection.
	 */
	protected CalEventMgr mgr;
	/** The currently-interesting year (not modulo 1900!) */
	protected int yy;
	/** Currently-interesting month and day */
	protected int mm, dd;
	/** The buttons to be displayed */
	protected JButton labs[][];
	/** The number of day squares to leave blank at the start of this month */
	protected int leadGap = 0;
	/** A Calendar object used throughout */
	final protected GregorianCalendar calendar = new GregorianCalendar();
	/** Today's year */
	protected final int thisYear = calendar.get(Calendar.YEAR);
	/** Today's month */
	protected final int thisMonth = calendar.get(Calendar.MONTH);
	/** One of the buttons. We just keep its reference for getBackground().*/
	private JButton b0;
	protected JLabel monthTF;

	/** Construct a CalMonth, starting with today. Can not collapse
	 * into a "this(...)" constructor since you can't call calendar.get()
	 * before calling super(). Been there. Done that. Didn't work :-)
	 */
	CalMonth(CalEventMgr cm) {
		super();
		setYYMMDD(calendar.get(Calendar.YEAR),
			calendar.get(Calendar.MONTH),
			calendar.get(Calendar.DAY_OF_MONTH));
		buildGUI();
		recompute();
		mgr = cm;
	}

	/** Construct a CalMonth, given the leading days and the total days
	 * @exception	IllegalArgumentException	If year out of range
	 */
	CalMonth(CalEventMgr cm, int year, int month, int today) {
		super();
		setYYMMDD(year, month, today);
		buildGUI();
		recompute();
		mgr = cm;
	}

	private void setYYMMDD(int year, int month, int today) {
		yy = year;
		mm = month;
		dd = today;
	}

	final String[] months = {
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
		monthTF = new JLabel(months[mm]);
		tp.add(monthTF);
		add(BorderLayout.NORTH, tp);

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
					setDayActive(dd = Integer.parseInt(num));
					// fire an event here to tell other CalXXX.
					mgr.fireEvent(new DateSelectedEvent(CalMonth.this,yy,mm,dd));
				}
			}
		};

		// Construct all the buttons, and add them.
		for (int i=0; i<6; i++)
			for (int j=0; j<7; j++) {
				bp.add(labs[i][j] = new JButton(""));
				labs[i][j].addActionListener(dateSetter);
			}

		add(BorderLayout.CENTER, bp);
	}

	public final static int dom[] = {
			31, 28, 31, 30,	/* jan feb mar apr */
			31, 30, 31, 31, /* may jun jul aug */
			30, 31, 30, 31	/* sep oct nov dec */
	};

	/** Compute which days to put where, in the CalMonth panel */
	protected void recompute() {
		// System.out.println("CalMonth::recompute: " + yy + ":" + mm + ":" + dd);
		if (mm < 0 || mm > 11)
			throw new IllegalArgumentException("Month " + mm + " bad, must be 0-11");

		monthTF.setText(months[mm]);

		clearDayActive();
		calendar.set(Calendar.YEAR, yy);
		calendar.set(Calendar.MONTH, mm);
		calendar.set(Calendar.DAY_OF_MONTH, dd);

		// Compute how much to leave before the first.
		// getDay() returns 0 for Sunday, which is just right.
		leadGap = new GregorianCalendar(yy, mm, 1).get(Calendar.DAY_OF_WEEK)-1;
		// System.out.println("leadGap = " + leadGap);

		int daysInMonth = dom[mm];
		if (calendar.isLeapYear(calendar.get(Calendar.YEAR)) && mm > 1)
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

		// Shade current day, if any
		if (dd >= 0)
			setDayActive(dd);		// shade the box for today

		// Say we need to be drawn on the screen
		repaint();
	}

	/** Called from other Cal objects to set the date in us. */
	public void dateSelected(DateSelectedEvent dse) {
		setYYMMDD(dse.yy, dse.mm, dse.dd);
		recompute();
	}

	/** Set the year, month, and day */
	public void setDate(int yy, int mm, int dd) {
		// System.out.println("CalMonth::setDate");
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
			b = labs[(leadGap+activeDay)/7][(leadGap+activeDay)%7];
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
			return;
		else
			dd = newDay;
		// Now shade the correct square
		Component square = labs[(leadGap+newDay)/7][(leadGap+newDay)%7];
		square.setBackground(Color.red);
		square.repaint();
		activeDay = newDay;
	}

	public String toString() {
		return getClass().getName() + '@' + hashCode();
	}

	/** For testing, a main program */
	public static void main(String[] av) {
		JFrame f = new JFrame("CalMonth");
		Container c = f.getContentPane();
		c.setLayout(new FlowLayout());

		CalEventMgr cm = new CalEventMgr();

		// for this test driver, hardcode 1995/02/10.
		c.add(new CalMonth(cm, 1995, 2-1, 10));

		// and beside it, the current month.
		c.add(new CalMonth(cm));

		f.pack();
		f.setVisible(true);
	}
}
