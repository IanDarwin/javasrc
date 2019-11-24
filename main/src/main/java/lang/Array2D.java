package lang;

public class Array2D {
	public static void main(String[] args) {
		int[][] data;
		data = new int[10][];
		data[0] = new int[5];
		data[1] = new int[20];
		System.out.println("data.length = " + data.length);
		System.out.println("data[0].length = " + data[0].length);
		System.out.println("data[1].length = " + data[1].length);
	}
}
