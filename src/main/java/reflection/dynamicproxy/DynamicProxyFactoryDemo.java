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
	static class QuoteServerFactory {
		public static QuoteServer getInstance() {

			QuoteServer objectBeingProxied = new QuoteServerImpl();

			InvocationHandler handler = new MyInvocationHandler(objectBeingProxied);

			return (QuoteServer) Proxy.newProxyInstance(
				QuoteServer.class.getClassLoader(),
	            new Class[] { QuoteServer.class }, handler);
		}
	}


	/** Here we show the whole thing in operation. */
	public static void main(String[] args) {

		QuoteServer quoter = QuoteServerFactory.getInstance();
		System.out.println("QuoteServer object is " + quoter.getClass().getName());
		quoter.addQuote("Only the educated are free -- Epictetus");
		System.out.println("QuoteServer returned: " + quoter.getQuote());
	}
}
