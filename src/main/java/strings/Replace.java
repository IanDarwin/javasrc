package strings;

public class Replace {
	public static void main(String[] args) {
		System.out.println(
			replace("lazy", "supine", "A quick bronze fox lept a lazy bovine"));
		System.out.println(
			replace("$DIR", "/home/ian", "$DIR/xxx"));
	}

	public static String replace(String oldStr, String newStr, String inString) {
		int start = inString.indexOf(oldStr);
		if (start == -1) {
			return inString;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(inString.substring(0, start));
		sb.append(newStr);
		sb.append(inString.substring(start+oldStr.length()));
		return sb.toString();
	}
} 
