package record;

/**
 * Demonstrate that records can implement any other methods wanted
 */
public record RecordsCanImplement(String name) implements Runnable {
	public void run() {
		System.out.println("In RecordsCanImplement::run");
	}

	public static void main(String[] args) {

		Runnable rec = new RecordsCanImplement("Patagonia");

		System.out.println(rec);

		rec.run();
	}
}

