package lang;

/** Just to show that there is no such thing as inner methods in Java */
public class InnerMethods {
	public static void main(String[] args) {
		new InnerMethods().work();
	}

	public void work() {	// EXPECT COMPILE ERROR
		void inner() {
			System.out.println("Hey, there is such a thing as Inner Methods");
			System.out.println("in Java. Better tell James Gosling and Sun!");
		}
		inner();
	}
}
