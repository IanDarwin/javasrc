package reflection.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


/**
 * A contrived demo of JavaSE's "dynamic proxy" mechanism.
 * Dynamic proxies allow you to create an implementation of
 * an interface without having to know the class name,
 * providing more flexibility.
 * @author Ian Darwin
 */
public class DynamicProxyDemo {


	/** Here we show the whole thing in operation. */
	public static void main(String[] args) {

		QuoteServer quoteServer = getQuoteServer();

		System.out.println("QuoteServer object is " + quoteServer.getClass().getName());
		quoteServer.addQuote("Only the educated are free -- Epictetus");
		System.out.println("QuoteServer returned: " + quoteServer.getQuote());
	}

	public static QuoteServer getQuoteServer() {
		QuoteServer objectBeingProxied = new QuoteServerImpl();
		InvocationHandler handler = new MyInvocationHandler(objectBeingProxied);
		return (QuoteServer) Proxy.newProxyInstance(
			QuoteServer.class.getClassLoader(),
            new Class[] { QuoteServer.class }, handler);
	}
}
