package functional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.util.stream.Stream;

public class LineCount {

	public static void main(String[] args) throws IOException {

		String fileName = "myfile.txt";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		long numberLines = br.lines().count();
		//Stream<String> lines = br.lines();
		//long numberLines = lines.count();
		br.close();
		System.out.println(fileName + " containss " + numberLines + " lines.");
	}
}
