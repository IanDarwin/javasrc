// This file is to show what happens if you try to access an inner class
// created in another class. Here we try to steal ButtonDemo2.java's
// inner class named MyActionListener. You will find that you can't,
// since it has no visible constructor!

class ButtonDemo2$1$MyActionListener {
}

public class TryStealingInnerClass {
	public static void main(String[] a) {
		Object o = new ButtonDemo2$1$MyActionListener();
		System.out.println(o.toString());
	}
}
