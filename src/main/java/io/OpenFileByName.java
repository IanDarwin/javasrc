import java.io.*;
public class OpenFileByName {
	public static void main(String[] args) throws IOException {
		BufferedReader is = new BufferedReader(new FileReader("myFile.txt"));
		BufferedOutputStream bytesOut = new BufferedOutputStream(
			new FileOutputStream("bytes.dat"));

		// Code here to read from is, write to bytesOut

		bytesOut.close();
	}
}
