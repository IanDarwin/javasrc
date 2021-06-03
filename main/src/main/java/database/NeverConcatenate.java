package database;

import java.io.*;

// If you don't know why this is here, see https://xkcd.com/327/

public class NeverConcatenate {

	public static void main(String[] args) throws IOException {

		String sql_template = "select * from students where name = '%s'";
		String name = System.console().readLine("Enter student login name: ");
		String sql_command = String.format(sql_template, name);
		System.out.println(sql_command);

	}
}

