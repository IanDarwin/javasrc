package io;

import java.io.*;

/**
 * Read and print, using BufferedReader from System.in, onto System.out
 */
// tag::main[]
public class CatStdin {

    public static void main(String[] av) {
        try (BufferedReader is =
            	new BufferedReader(new InputStreamReader(System.in))) {
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
// end::main[]
