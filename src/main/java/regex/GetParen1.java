import java.util.regex.*;

public class GetParen1 {
	public static void main(String[] args) {
		Pattern patt = Pattern.compile("(\\w+)\\s(\\d+)"); 
		Matcher matcher = patt.matcher("Bananas 123"); 
		matcher.lookingAt();
		System.out.println("Name: " + matcher.group(1)); 
		System.out.println("Number: " + matcher.group(2)); 
	}
}
