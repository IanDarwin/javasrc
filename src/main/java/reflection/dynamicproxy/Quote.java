package lang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** The interface that the impl and the proxy both implement. */
interface Maxim {
	public String getMaxim();
	public void addMaxim(String newQuote);
}

/**
 * A contrived demo of the "dynamic proxy" mechanism in J2SE.
 * @author ian
 */
public class ProxyDemo {

	static Maxim privateImpl = new MaximImpl();
	
	/** The Handler, which is called whenever a method on the proxy is invoked.
	 * Note that the invoke() method only needs a reference to the implementation
	 * object; it does not need to figure out which method to call, because it's
	 * passed a Method descriptor.
	 */
	private static InvocationHandler handler = new InvocationHandler() {
		public Object invoke(Object proxyObject, Method method, Object[] argList) 
			throws Throwable {
			System.out.println("Proxy: about to invoke " + method.getName());
			Object ret = method.invoke(privateImpl, argList);
			System.out.println("Proxy: Invocation done.");
			return ret;
		}		
	};

	/** Here's where we show the whole thing in operation.
	 */
	public static void main(String[] args) {

		Maxim m = (Maxim) Proxy.newProxyInstance(Maxim.class.getClassLoader(),
            new Class[] { Maxim.class }, handler);
		System.out.println("Maxim Proxy object is " + m.getClass().getName());
		m.addMaxim("A stitch in time... is better late than never");
		m.addMaxim("JavaScript is to Java as George Burns is to George Washington.");
		System.out.println("Maxim Proxy returned: " + m.getMaxim());
	}
}

/** Our private implementation of the interface */
class MaximImpl implements Maxim {
	final List sayings;
	
	MaximImpl() {
		sayings = new ArrayList();
		String first = "The older you get, the more you forget";
		sayings.add(first);
	}

	Random r = new Random();
	public String getMaxim() {
		// Get an int in 0..list.size()-1, get that saying from the list.
		return (String)sayings.get((int)(Math.random()*sayings.size()));
	}
	public void addMaxim(String s) {
		sayings.add(s);
	}
};