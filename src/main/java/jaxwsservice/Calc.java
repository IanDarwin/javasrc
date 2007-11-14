package jaxwsservice.toy;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Calc {
	
	public int add(int a, int b) {
		System.out.println("CalcImpl.add()");
		return a + b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}
	
	public int divide(int a, int b) {
		if (b == 0) {
			throw new ArithmeticException("Divide by 0");
		}
		return a / b;
	}
}