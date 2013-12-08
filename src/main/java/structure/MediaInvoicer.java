package structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * MediaInvoicer - Simple application of Media enum
 * @author Ian Darwin
 */
public class MediaInvoicer {

	public static void main(String[] args) throws IOException {
		MediaInvoicer mi = new MediaInvoicer(System.in);
		Invoice i = mi.getInvoice();
		i.print(System.out);
	}
	BufferedReader myFile;
	public MediaInvoicer(InputStream is) {
		myFile = new BufferedReader(new InputStreamReader(is));
	}

	public Invoice getInvoice() throws IOException {
		String line;
		List < Item > items = new ArrayList < Item > ();
		while ((line = myFile.readLine()) != null) {
			if (line.startsWith("#")) {
				continue;
			}
			StringTokenizer st = new StringTokenizer(line);
			st.nextToken();
			Media m = Media.getMedia(st.nextToken());
			int stock = Integer.parseInt(st.nextToken());
			int qty = Integer.parseInt(st.nextToken());
			Item tmp = new Item(m, stock, qty);
			items.add(tmp);
		}
		return new Invoice(1, 3,
			(Item[]) items.toArray(new Item[items.size()]));
	}

	/** Inner class for line order item */
	class Item {
		Media product;
		int stockNumber;
		int quantity;
		/**
		 * @param product
		 * @param stockNumber
		 * @param quantity
		 */
		public Item(Media product, int stockNumber, int quantity) {
			super();
			this.product = product;
			this.stockNumber = stockNumber;
			this.quantity = quantity;
		}
		public String toString() {
			return "Item[" + product + " " + stockNumber + "]";
		}
	}
	/** Inner class for one invoice */
	class Invoice {
		int orderNumber;
		int custNumber;
		Item[] items;

		public Invoice(int orderNumber, int custNumber, Item[] items) {
			super();
			this.orderNumber = orderNumber;
			this.custNumber = custNumber;
			this.items = items;
		}
		public void print(PrintStream ps) {
			ps.println("*** Invoice ***");
			ps.println("Customer: " + custNumber + ")");
			ps.println("Our order number: " + orderNumber);
			for (int i = 0; i < items.length; i++) {
				Item it = items[i];
				ps.println(it);
			}
		}
	}
}
