package sidebyside.java;

import java.util.Scanner;

public class AcceptDemo {
	public static void main(String[] args) {
	int num1, num2, result;
	Scanner sc = new Scanner(System.in);      // Requires J2SE 1.5
	num1 = sc.nextInt();
	num2 = sc.nextInt();
	result = num1 * num2;
	System.out.println("Result = " + result);
	}
}

