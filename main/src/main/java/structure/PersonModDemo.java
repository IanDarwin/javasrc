package structure;

public class PersonModDemo {

	public record PersonMod(String name, String email, String phoneNumber) {

		public PersonMod withPhoneNumber(String number) {
			return new PersonMod(name(), email(), number);
		}
	}

	public static void main(String[] args) {
		PersonMod p = new PersonMod("Robin Smith", "robin_smith@example.com", "555-1234");
		System.out.println(p);
		p = p.withPhoneNumber("555-9876");
		System.out.println(p);
	}
}
