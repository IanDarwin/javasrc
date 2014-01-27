package datetimeold;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DateFormat;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.ibm.icu.util.Calendar;

public class IBMCalDemo {
	public static void main(String[] args) {

		Locale ar_loc = new Locale("ar");

		Calendar c = new com.ibm.icu.util.IslamicCalendar();

		DateFormat d = DateFormat.getDateInstance(DateFormat.LONG, ar_loc);

		String ar_date = d.format(c.getTime());
		System.out.println(ar_date);

		JFrame jf = new JFrame();
		Container cp = jf.getContentPane();
		cp.setLayout(new FlowLayout());
		JComponent demo = new JLabel(ar_date);
		demo.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		cp.add(demo);
		jf.pack();
		jf.setVisible(true);
	}
}
