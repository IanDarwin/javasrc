package reflection;

import java.lang.reflect.Field;

/**
 * Demonstrate that it is, in fact, all too easy to access private members
 * of an object using Reflection, using the default SecurityManager (so this
 * will probably not work in an Applet, for example...).
 */
public class DefeatPrivacy {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		new DefeatPrivacy().process();

	}
	
	class X {
		private int p = 42;
		int q = 3;
		public int r = 0x123;
		public int getP() {
			return p;
		}
	}

	private void process() throws Exception {
		X x = new X();
		System.out.println(x);
		Class<? extends X> class1 = x.getClass();
		Field[] flds = class1.getDeclaredFields();
		for (Field f : flds) {
			f.setAccessible(true);	// bye-bye "private"
			System.out.println(f + "==" + f.get(x));
		}
	}

}
