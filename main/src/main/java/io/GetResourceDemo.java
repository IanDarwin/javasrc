// tag::main[]
package io;

import java.io.*;

public class GetResourceDemo {
	private static final String MY_CONFIG_FILE = "myconfig.properties";

	public static void main(String[] args) throws Exception {
		Class<?> c = GetResourceDemo.class;

		// By file name
		System.out.println("Class is " + c.getName());
		final String pathName1 = "/getresourcedemo1.txt";
		System.out.println("pathName1 = " + pathName1);
		InputStream isOne = c.getResourceAsStream(pathName1);
		System.out.println("InputStream One = " + isOne);
		displayContents(isOne); // simple convenience routing

		// By package-relative name
		final String name = c.getPackage().getName();
		final String fullPathName = "/" + name.replace('.', '/') + "/" + MY_CONFIG_FILE;
		System.out.println("pathName2 = " + fullPathName);
		InputStream isTwo = c.getResourceAsStream(fullPathName);
		System.out.println("InputStream Two = " + isTwo);
		displayContents(isTwo);
	}
	// end::main[]

	static void displayContents(InputStream is) throws IOException {
		if (is == null) {
			return;
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			System.out.println(br.readLine());
		}
	}

}