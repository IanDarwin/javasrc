package reflection.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


/**
 * A contrived demo of the "dynamic proxy" mechanism in J2SE.
 * Dynamic proxies allow you to create an implementation of
 * an interface without having to know the class name,
 * providing more flexibility.
 * @author Ian Darwin
 */
public class DynamicProxyFactoryDemo {

	/** Factory used to create the objects;
	 * could decide to return proxied or non-proxied versions,
	 * depending upon the application.
	 */
	static class QuoteFactory {
		public static Quote getInstance() {

			Quote objectBeingProxied = new QuoteImpl();

			InvocationHandler handler = new MyInvocationHandler(objectBeingProxied);

			return (Quote) Proxy.newProxyInstance(
				Quote.class.getClassLoader(),
	            new Class[] { Quote.class }, handler);
		}
	}


	/** Here we show the whole thing in operation. */
	public static void main(String[] args) {

		Quote quoter = QuoteFactory.getInstance();
		System.out.println("Quote Proxy object is " + quoter.getClass().getName());
		quoter.addQuote("Only the educated are free -- Epictetus");
		System.out.println("Quote Proxy returned: " + quoter.getQuote());
	}
}
