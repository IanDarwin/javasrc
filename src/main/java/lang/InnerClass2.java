package lang;

import java.util.Date;

/** Demonstrate simple inner class which just extends an
 * existing class (Date) by overriding one method in it.
 */
public class InnerClass2 {

	void doWork() {
		Date d = new Date() {
			private static final long serialVersionUID = 1L;

			public String toString() {
				return "Today is " + super.toString();
			}
		};
		System.out.println("My Date: " + d);
		System.out.println("Std Date: " + new Date());
	}

	public static void main(String[] av) {
		InnerClass2 p = new InnerClass2();
		p.doWork();
	}
}
