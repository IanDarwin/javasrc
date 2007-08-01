package reflection;

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

/** Our private implementation of the interface */
class QuoteImpl implements Quote {

	final List<String> sayings = new ArrayList<String>();

	QuoteImpl() {
		sayings.add("A stitch in time... is better late than never");
		sayings.add("JavaScript is to Java as George Burns is to George Washington.");
		sayings.add("The more old you get, the more you forget");
	}

	public String getQuote() {
		// Get an int in 0..list.size()-1, get that saying from the list.
		return (String)sayings.get((int)(Math.random()*sayings.size()));
	}

	public void addQuote(String newQuote) {
		sayings.add(newQuote);
	}
}

/** The Handler, which is called whenever a method on the proxy is invoked.
 * Note that the invoke() method only needs a reference to the implementation
 * object; it does not need to figure out which method to call, because it's
 * passed a Method descriptor.
 */
class MyInvocationHandler implements InvocationHandler {

	private Object realObject;

	public MyInvocationHandler(Object realObject) {
		super();
		this.realObject = realObject;
	}

	/**
	 * Method that is called for every call into the proxy;
	 * this has to invoke the method on the real object.
	 */
	public Object invoke(Object proxyObject, Method method, Object[] argList)
		throws Throwable {
		System.out.print(
			"Proxy: invoking " + method.getName() + "()... ");
		Object ret = method.invoke(realObject, argList);
		System.out.println(" Invocation done.");
		return ret;
	}
}

/**
 * A contrived demo of the "dynamic proxy" mechanism in J2SE.
 * Dynamic proxies allow you to create an implementation of
 * an interface without having to know the class name,
 * providing more flexibility.
 * @author Ian Darwin
 */
public class DynamicProxyDemo {

	static Quote objectBeingProxied = new QuoteImpl();

	/** Here we show the whole thing in operation. */
	public static void main(String[] args) {

		InvocationHandler handler = new MyInvocationHandler(objectBeingProxied);

		Quote generatedProxy = (Quote) Proxy.newProxyInstance(
			Quote.class.getClassLoader(),
            new Class[] { Quote.class }, handler);

		System.out.println("Quote Proxy object is " + generatedProxy.getClass().getName());
		objectBeingProxied.addQuote("Only the educated are free -- Epictetus");
		System.out.println("Quote Proxy returned: " + generatedProxy.getQuote());
	}
}
