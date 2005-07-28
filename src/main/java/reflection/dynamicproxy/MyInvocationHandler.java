package lang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** The interface that the impl and the proxy both implement. */
interface Quote {
	public String getQuote();
	public void addQuote(String newQuote);
}

/**
 * A contrived demo of the "dynamic proxy" mechanism in J2SE.
 * @author Ian Darwin
 */
public class ProxyDemo {

	/** Our private implementation of the interface */
	static final Quote privateImpl = new Quote() {
		final List sayings = new ArrayList();
		Random r = new Random();
		
		public String getQuote() {
			// Get an int in 0..list.size()-1, get that saying from the list.
			return (String)sayings.get((int)(Math.random()*sayings.size()));
		}
		public void addQuote(String s) {
			sayings.add(s);
		}
	};
	
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

		Quote m = (Quote) Proxy.newProxyInstance(Quote.class.getClassLoader(),
            new Class[] { Quote.class }, handler);
		System.out.println("Quote Proxy object is " + m.getClass().getName());
		m.addQuote("A stitch in time... is better late than never");
		m.addQuote("JavaScript is to Java as George Burns is to George Washington.");
		m.addQuote("The more old you get, the more you forget");
		System.out.println("Quote Proxy returned: " + m.getQuote());
	}
}
