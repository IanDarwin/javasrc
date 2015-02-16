package lang;

public class AndOp {

	public static void main(String[] args) {
		boolean taf = true & false;
		boolean fat = false & true;
		boolean taaf = true && false;
		boolean faat = false && true;
		System.out.println(taf + "," + fat + "," + taaf + "," +faat);
	}

}
