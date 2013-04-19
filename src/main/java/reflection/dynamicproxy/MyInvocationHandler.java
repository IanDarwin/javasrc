package reflection.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** The InvocationHandler, called whenever any method on the proxy is invoked.
 * Note that the invoke() method only needs a reference to the implementation
 * object; it does not (necessarily) need to figure out which method to call,
 * because it's passed a Method descriptor.
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
			"Proxy invoking " + method.getName() + "()... ");
		Object ret = method.invoke(realObject, argList);
		System.out.println(" Completed.");
		return ret;
	}
}

