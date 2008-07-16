package sidebyside.java;

import java.util.Scanner;

public class AcceptDemo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);      // Requires J2SE 1.5
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int result = num1 * num2;
		System.out.println("Result = " + result);
	}
}
