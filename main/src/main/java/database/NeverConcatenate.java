package database;

// If you don't know why this is here, see https://xkcd.com/327/
// Meanwhile, DO NOT DO THIS in production!
import java.io.Console;

public class NeverConcatenate {

	final static String sql_template = "select * from students where name = '%s'";
	final static Console console = System.console();

	public static void main(String[] args) {

		while (true) {
			String name = console.readLine("Enter student login name: ");
			String sql_command = sql_template.formatted(name);
			System.out.println(sql_command);
			if (sql_command.indexOf("';") > 0)
				System.out.println("Looks like you mighta got pwned, pardner!");
			else
				System.out.println("What a nice name!");
		}
	}
}
