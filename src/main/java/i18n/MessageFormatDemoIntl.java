package i18n;

import java.text.*;
import java.util.*;

// BEGIN main
public class MessageFormatDemoIntl {

	private static Date date = new Date();
	private static String fileName = "myfile.txt";

	public static void main(String[] args) {
		ResourceBundle rb = ResourceBundle.getBundle("Widgets");
		String format = rb.getString("filedialogs.cantopen.format");
		String result = MessageFormat.format(format, date, fileName);
		System.out.println(result);
	}
}
// END main
