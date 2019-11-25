package jsp;

import java.util.Random;

public class FiveInts {
	public static void main(String[] argv) {
		//+
		Random r = new Random();
		for (int i=0; i<5; i++)
			System.out.println(r.nextInt());
		//-
	}
}
