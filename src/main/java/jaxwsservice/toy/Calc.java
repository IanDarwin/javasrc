package jaxwsservice.toy;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace="http://toy.service/")
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class Calc {
	
	public int add(int a, int b) {
		System.out.println("CalcImpl.add()");
		return a + b;
	}
	
	public int subtract(int a, int b) {
		System.out.println("CalcImpl.add()");
		return a - b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}
	
	public int divide(int a, int b) {
		if (b == 0) {
			// slightly better-than-average message
			throw new ArithmeticException(
				"You tried to divide " + a + " by 0");
		}
		return a / b;
	}
}
