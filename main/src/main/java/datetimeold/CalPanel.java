package datetimeold;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JPanel;

/** A JPanel to display a "Cal" calendar component */
public class CalPanel extends JPanel {

	private static final long serialVersionUID = 4524198484331822351L;
	int yy, mm, dd;
	Cal cal;
	TextField yyText, mmText, ddText;

	public CalPanel() {
		setLayout(new BorderLayout());

		LocalDate now = LocalDate.now();
		yy = now.getYear();
		mm = now.getMonthValue();
		dd = now.getDayOfMonth();

		add(cal = new Cal(yy, mm, dd), BorderLayout.NORTH);

		// The action for changing yy or mm draws a new calendar
		final ActionListener listener = e -> {
			System.out.println("CalPanel::ActionPerformed");
			yy = Integer.parseInt(yyText.getText());
			mm = Integer.parseInt(mmText.getText());
			dd = Integer.parseInt(ddText.getText());
			
			cal.setDate(yy, mm-1, dd);
		};
		add(yyText = new TextField(""+yy));
		yyText.addActionListener(listener);

		add(mmText = new TextField(""+(mm+1)));
		mmText.addActionListener(listener);

		// The action for changing the day just highlights that day.
		add(ddText = new TextField(""+dd));
		ddText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cal.setDayActive(Integer.parseInt(ddText.getText()));
			}
		});
	}
}
