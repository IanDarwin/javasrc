package strings;

/** Just because we have a library does not mean we should forget basic coding */
public class StrUtil {
	
	/** Reverse a String "in place" after extracting array from String object */
	public static String reverseInPlace(String s ) {
        int length = s.length(), last = length - 1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < length/2; i++ ) {
            char c = chars[i];
            chars[i] = chars[last - i];
            chars[last - i] = c;
        }
        return new String(chars);
    }
	
	/** Reverse a String by copying to new array */
	public static String reverseNew(String s) {
		int length = s.length();
		char[] rev = new char[length];
		for (int i = 0; i < length; i++) {
			rev[i] = s.charAt(length - i -1);
		}
		return new String(rev);
	}
}
