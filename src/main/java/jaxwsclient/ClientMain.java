package jaxwsclient;


public class ClientMain {
	public static void main(String[] args) {
		Calc client = new CalcService().getCalcPort();
		System.out.println("add(34,66) = " + client.add(34,66));
	}
}
