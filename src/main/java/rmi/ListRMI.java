package rmi;

import java.rmi.*;

public class ListRMI {
	public static void main(String[] args) throws Exception {

		String theURL = "rmi://localhost:1099/";

		String[] names = Naming.list(theURL);
		for (int i=0; i<names.length; i++) {
			System.out.println(i + ": " + names[i] + "\n");
		}

	}
}
