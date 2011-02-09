package jaxwsclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ClientMain {
	public static void main(String[] args) throws IOException {
		BufferedReader con = new BufferedReader(new InputStreamReader(System.in));
		Calc client = new CalcService().getCalcPort();
		String line = null;
		System.out.println("Interactive calculator. Put spaces around operators.");
		do {
			System.out.print(">> "); System.out.flush();
			if ((line = con.readLine()) == null) {
				return;
			}
			if (line.length() == 0 || line.startsWith("#")) {
				continue;
			}
			StringTokenizer st = new StringTokenizer(line);
			if (st.countTokens() != 3) {
				System.err.println("Don't like this line: " + line);
				continue;
			}
			int arg1 = 0; 
			try {
				arg1 = Integer.parseInt(st.nextToken());
			} catch (NumberFormatException e) {
				System.err.println("First number invalid");
				continue;
			}
			char op = st.nextToken().charAt(0);
			int arg2 = 0; 
			try {
				arg2 = Integer.parseInt(st.nextToken());
			} catch (NumberFormatException e) {
				System.err.println("Second number invalid");
				continue;
			}
			int ret = 0;
			try {
				switch(op) {
				case '+':
					ret = client.add(arg1, arg2);
					break;
				case '-':
					ret = client.subtract(arg1, arg2);
					break;
				case '*':
					ret = client.multiply(arg1, arg2);
					break;
				case '/':
					ret = client.divide(arg1, arg2);
					break;
				}
			} catch (RuntimeException e) {
				System.err.println(line + " caused an exception");
				System.err.println(e.toString());
				System.err.flush();
				continue;
			}
			System.out.printf("%s = %d%n", line, ret);
		} while (line != null);
	}
}
