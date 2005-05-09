package cookiecutter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class CookieAccessorTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<Cookie> al = new CookieAccessor().read("cookies.txt");
		System.out.println(al.size());
		System.out.println(al.get(12));
	}
}
