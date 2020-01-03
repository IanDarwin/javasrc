package structure;

public class MyListDemo {
	public static void main(String[] args) {
		MyList list = MyList.of("Hello","there","world");
		list.forEach(System.out::println);
	}
}
