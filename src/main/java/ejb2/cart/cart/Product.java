package ejb2.cart.src.cart;

/** Dummy Product class, serializable for RMI use */
public class Product implements java.io.Serializable {

	public int itemNumber;

	public String name;

	public double price;

	public int quantity;

	public Product(int inum) {
		itemNumber = inum;
	}
}
