package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetParen0 {
	public static void main(String[] args) {
		Pattern myRE = Pattern.compile("d.*ian");
		Matcher matcher = myRE.matcher(
		"darwinian pterodactyls soared over the devonian space");
		matcher.lookingAt();
		String result = matcher.group(0);
		System.out.println(result);
	}
}
