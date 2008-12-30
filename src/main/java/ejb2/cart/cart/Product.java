package ejb2.cart.cart;

/** Dummy Product class, serializable for RMI use */
public class Product implements java.io.Serializable {

	private static final long serialVersionUID = -8825206928969978074L;

	public int itemNumber;

	public String name;

	public double price;

	public int quantity;

	public Product(int inum) {
		itemNumber = inum;
	}
}
