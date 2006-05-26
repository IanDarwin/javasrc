package DBM;

import java.io.*;
import java.util.*;

public class TestDBM {
	
	public static void main(String args[]) throws IOException {
		DBM d = new DBM("/tmp/mydb");
		System.out.println(d);
		d.store("today", new Date());
		d.store("here", System.getProperty("user.dir"));
		DBM d2;
		try { 
			d2 = new DBM("NoSuchDbAnyway"); 
			System.out.println(d2);
			System.out.println("** ERROR ** failed to throw Exception");
		} catch (IllegalArgumentException e) {
			System.out.println("Correctly threw " + e);
		}
		d.close();

		System.out.println("Now close, re-open, and fetch");
		d2 = new DBM("/tmp/mydb");
		System.out.println("d2 contains " + d2.fetch("here"));
		System.out.println("d2 contains " + d2.fetch("today"));
		d2.close();

		System.out.println("Now try iterating");
		DBM d3 = new DBM("/tmp/mydb");
		Object o;
		for (o = d3.firstkeyObject(); o != null; o = d3.nextkey(o)) {
			System.out.println("Key=\"" + o + "\"; " +
				"value=\"" + d3.fetch(o) + "\"");
		}
	}
}
