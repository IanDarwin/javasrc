package hibernate;

public class DemoDAODemo {
	public static void main(String[] args) {
		String customerName = new DemoDAO().read(Integer.valueOf(42));
		System.out.println(customerName);
	}
}
