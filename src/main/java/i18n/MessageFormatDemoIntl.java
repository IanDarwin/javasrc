package i18n;

import java.text.*;
import java.util.*;

public class MessageFormatDemoIntl {

	static Object[] data = {
			new Date(),
			"myfile.txt",
			null
	};

	public static void main(String[] args) {
		ResourceBundle rb = ResourceBundle.getBundle("Widgets");
		data[2] = rb.getString("filedialogs.cantopen.string");
		String result = MessageFormat.format(
			rb.getString("filedialogs.cantopen.format"), data);
		System.out.println(result);
	}
}
