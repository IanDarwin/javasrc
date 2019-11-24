package io;

import java.io.*;

/**
 * Vis - make special characters visible.
 */
public class Vis {

    public static void main(String[] av) {
        Vis v = new Vis();
        if (av.length == 0)
            v.process(new BufferedReader(
				new InputStreamReader(System.in)));
		else for (int i=0; i<av.length; i++)
			try {
				v.process(new BufferedReader(new FileReader(av[i])));
			} catch (FileNotFoundException e) {
				System.err.println(e);
			}
    }

    /** print one file, given an open BufferedReader */
    public void process(BufferedReader is) {
        try {
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
                for (int i=0; i<inputLine.length(); i++){
					char c = inputLine.charAt(i);
					switch(c) {
						case '\t': System.out.print("\\t"); break;
						case '\r': System.out.print("\\r"); break;
						case '\n': System.out.print("\\n"); break;
						default: System.out.print(c); break;
					}
				}
				System.out.println();
            }
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
