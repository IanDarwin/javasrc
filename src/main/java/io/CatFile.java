package io;

import java.io.*;

/**
 * Read a file and print, using BufferedReader and System.out
 */
public class CatFile {

    public static void main(String[] av) {
        CatFile c = new CatFile();
        if (av.length == 0)
            c.process(new BufferedReader(
					new InputStreamReader(System.in)));
		else for (int i=0; i<av.length; i++)
			try {
				c.process(new BufferedReader(new FileReader(av[i])));
			} catch (FileNotFoundException e) {
				System.err.println(e);
			}
    }

    /** print one file, given an open BufferedReader */
    public void process(BufferedReader is) {
        try {
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
                System.out.println(inputLine);
            }
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
