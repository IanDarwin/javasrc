package regex;

import java.util.regex.Pattern;

public class CommentedRegEx {

	public static void main(String[] args) {
		// tag::main[]
		String regex = 
				"\\w+   # starts with a word" +
				"\\s+   # then some space" +
				"\\d+   # and ends with a number";
		Pattern p = Pattern.compile(regex , Pattern.COMMENTS);
		if (p.matcher("Ian 999").find()) {
			System.out.println("OK");
		} else {
			System.out.println("Fail");
		}
		// end::main[]
	}

}
