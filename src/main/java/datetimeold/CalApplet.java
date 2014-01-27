package datetimeold;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

/** An Applet to display a "Cal" calendar component */
public class CalApplet extends Applet implements ActionListener {

	private static final long serialVersionUID = 4524198484331822351L;
	int yy, mm, dd;
	Cal cal;
	Panel p;
	TextField yyText, mmText, ddText;

	public void init() {
		setLayout(new BorderLayout());

		Calendar d = new GregorianCalendar();
		yy = d.get(Calendar.YEAR);
		mm = d.get(Calendar.MONTH);
		dd = d.get(Calendar.DAY_OF_MONTH);

		add(cal    = new Cal(yy, mm, dd), BorderLayout.NORTH);
		p = new Panel();
		p.setLayout(new FlowLayout());

		// The action for changing yy or mm draws a new calendar
		p.add(yyText = new TextField(""+yy));
		yyText.addActionListener(this);
		p.add(mmText = new TextField(""+(mm+1)));
		mmText.addActionListener(this);

		// The action for changing the day just highlights that day.
		p.add(ddText = new TextField(""+dd));
		ddText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cal.setDayActive(Integer.parseInt(ddText.getText()));
			}
		});
		add(p, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("CalApplet::ActionPerformed");
		yy = Integer.parseInt(yyText.getText());
		mm = Integer.parseInt(mmText.getText());
		dd = Integer.parseInt(ddText.getText());

		cal.setDate(yy, mm-1, dd);
	}
}
