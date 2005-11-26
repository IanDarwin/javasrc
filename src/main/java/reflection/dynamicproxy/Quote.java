package lang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/** The interface that the impl and the proxy both implement. */
interface Quote {
	public String getQuote();
	public void addQuote(String newQuote);
}

/**
 * A contrived demo of the "dynamic proxy" mechanism in J2SE.
 * Dynamic proxies allow you to create an implementation of
 * an interface without having to know the class name,
 * providing more flexibility.
 * @author Ian Darwin
 */
public class ProxyDemo {

	/** Our private implementation of the interface */
	static final Quote privateImpl = new Quote() {
		
		final List<String> sayings = new ArrayList<String>();
		
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
			System.out.print("Proxy: invoking " + method.getName());
			Object ret = method.invoke(privateImpl, argList);
			System.out.println(" Invocation done.");
			return ret;
		}		
	};

	/** Here we show the whole thing in operation.
	 */
	public static void main(String[] args) {

		Quote q = (Quote) Proxy.newProxyInstance(
			Quote.class.getClassLoader(),
            new Class[] { Quote.class }, handler);
		System.out.println("Quote Proxy object is " + q.getClass().getName());
		q.addQuote("A stitch in time... is better late than never");
		q.addQuote("JavaScript is to Java as George Burns is to George Washington.");
		q.addQuote("The more old you get, the more you forget");
		System.out.println("Quote Proxy returned: " + q.getQuote());
	}
}
