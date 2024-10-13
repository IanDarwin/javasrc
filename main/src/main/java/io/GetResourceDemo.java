package io;

import java.io.*;

public class GetResourceDemo {
	private static final String MY_CONFIG_FILE = "myconfig.properties";

	public static void main(String[] args) throws Exception {
		Class<?> c = GetResourceDemo.class;
		System.out.println("Class is " + c.getName());
		InputStream isOne = c.getResourceAsStream("/getresourcedemo1.txt");
		System.out.println("InputStream One = " + isOne);
		displayContents(isOne);

		final String name = c.getPackage().getName();
		final String fullPathName = "/" + name.replace('.', '/') + "/" + MY_CONFIG_FILE;
		System.out.println(fullPathName);
		InputStream isTwo = c.getResourceAsStream(fullPathName);
		System.out.println("InputStream Two = " + isTwo);
		displayContents(isTwo);
	}

	static void displayContents(InputStream is) throws IOException {
		if (is == null) {
			return;
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			System.out.println(br.readLine());
		}
	}

}