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
