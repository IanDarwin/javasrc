import java.io.*;
import java.util.*;

public class test {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Vector al = new CookieAccessor().read("cookies.txt");
		System.out.println(al.size());
		System.out.println(al.get(12));
	}
}
