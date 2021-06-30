package structure;

public class RecordDemoPerson {

	public record Person(String name, String email) { }

	public static void main(String[] args) {
		Person p = new Person("Covington Roderick Smythe", "roddy@smythe.tld");
		System.out.println(p);
		System.out.println(p.name);
		System.out.println(p.name());
	}
}
