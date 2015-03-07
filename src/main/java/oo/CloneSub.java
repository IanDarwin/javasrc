package oo;

class One extends java.lang.Object {
	int x;
}

class Two extends One /*implements Cloneable*/ {
	int y;
	public void foo() {
		try {
			/*Object o =*/ this.clone();
		} catch (CloneNotSupportedException ex) {
			System.err.println(ex);
		}
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

public class CloneSub {
	public static void main(String[] args) throws CloneNotSupportedException {
		Two t1 = new Two();
		t1.x = 100;
		t1.y = 200;
		Two t2 = (Two)t1.clone();
		System.out.println(t2);
		System.out.println(t2.y);
	}
}
