package io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class OpenFileByName {
	public static void main(String[] args) throws IOException {
		BufferedReader is = new BufferedReader(new FileReader("myFile.txt"));
		BufferedOutputStream bytesOut = new BufferedOutputStream(
			new FileOutputStream("bytes.dat"));

		// Code here to read from is, write to bytesOut

		bytesOut.close();
		is.close();
	}
}
