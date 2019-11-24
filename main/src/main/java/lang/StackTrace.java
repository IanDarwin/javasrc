package lang;

/**
 * Every "Exception" (or subclass) object contains a "stackTrace", or
 * traceback, meant to indicate where the error occurred.
 *
 * Let's find out where a stackTrace comes from, and how to use it, when
 * exceptions are created and thrown. Some textbooks claim that it is
 * the operation of constructing the exception that anchors its trace,
 * others the throwing. Let us see for ourselves.
 */
public class StackTrace {

	IllegalArgumentException ex;

	public static void main(String[] argv) {
		StackTrace st = new StackTrace();
		st.makeit();
		System.out.println("CONSTRUCTED BUT NOT THROWN");
		st.ex.printStackTrace();	
		st.throwit();
		// MAY BE NOTREACHED - THINK ABOUT IT!
		System.out.println("CONSTRUCTED BUT NOT THROWN");
		st.ex.printStackTrace();
	}

	public void makeit() {
		ex = new IllegalArgumentException("Don't like the weather today");
	}
	public void throwit() throws IllegalArgumentException {
		throw ex;
	}
}
