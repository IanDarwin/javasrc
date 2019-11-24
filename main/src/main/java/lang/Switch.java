package lang;

public class Switch {
	static short s;
	static int   i;
	static long  L;
	public static void main(String[] args) { 
		switch(s) {
			case 0: System.out.println(s);
		}
		switch(i) {
			case 0: System.out.println(i);
		}
		switch(L) {		// EXPECT COMPILER ERROR
			case 0: System.out.println(L);
		}
	}
}
