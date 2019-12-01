package com.darwinsys.testing;

import org.junit.Test;

public class CheckAccessorsTest {

	// JUnit 4 assert*() methods throw java.lang.AssertionError;
	// TestAccessors on BadClass should throw this exception
	// due to the deliberate bug in BadClass (see below).
	@Test(expected=AssertionError.class)
	public final void testBadClass() throws Exception {
		CheckAccessors.process(BadClass.class);
	}
	
	@Test
	public final void testGoodClass() throws Exception {
		CheckAccessors.process(GoodClass.class);
	}
	
	/** test data class with various types */
	static class GoodClass {
		char ch;
		int i;
		double d;
		float f;
		Integer ii;
		public char getCh() {
			return ch;
		}
		public void setCh(char ch) {
			this.ch = ch;
		}
		public double getD() {
			return d;
		}
		public void setD(double d) {
			this.d = d;
		}
		public float getF() {
			return f;
		}
		public void setF(float f) {
			this.f = f;
		}
		public int getI() {
			return i;
		}
		public void setI(int i) {
			this.i = i;
		}
		public Integer getIi() {
			return ii;
		}
		public void setIi(Integer ii) {
			this.ii = ii;
		}
	}

	/** test data class, contains deliberate error
	 * (setter returns wrong value)
	 */
	public static class BadClass {
		int i,j;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		public int getJ() {
			return i; // deliberate error DO NOT FIX
		}

		public void setJ(int j) {
			this.j = j; // FindBugs "M P UrF" - expected
		}		
	}
}
