package oo;

public class Clone0 {
	public static void main(String[] args) { 
		Object o = new Object();
		Object o2 = o.clone();	// EXPECT COMPILE ERROR
	}
}
