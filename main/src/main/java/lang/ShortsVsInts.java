package lang;

public class ShortsVsInts {
	public static void main(String[] unused) {
		short i,j;
		i = 30;
		j = ++i;	// works
		j += 1;		// works
		j += 32768;	// compiles; truncates at run time!
		System.out.println(j);
		System.out.println(Short.MAX_VALUE);
		//j = j + 1;	// won't compile
	}
}
