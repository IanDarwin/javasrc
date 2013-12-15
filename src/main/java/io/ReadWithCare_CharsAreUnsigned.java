package io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadWithCare_CharsAreUnsigned {
	
	private void test(InputStream is) {
		char c;
		try {
			while ((c = (char) is.read()) != -1) {
				System.out.println(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Foo.main()");
		ReadWithCare_CharsAreUnsigned me = 
			new ReadWithCare_CharsAreUnsigned();
		me.test(new ByteArrayInputStream("Joy to the world!".getBytes()));
	}
}
