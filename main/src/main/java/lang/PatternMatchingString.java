import java.util.List;

sealed abstract class Op permits Add, Subtract, Multiply, Divide {
	int left;
	int right;
	Op(int left, int right) {
		this.left = left;
		this.right = right;
	}
}

final class Add extends Op { Add(int x, int y) { super(x, y); } }

final class Subtract extends Op { Subtract(int x, int y) { super(x, y); } }

final class Multiply extends Op { Multiply(int x, int y) { super(x, y); } }

final class Divide extends Op { Divide(int x, int y) { super(x, y); } }

void main() {
	// tag::oldway[]
	System.out.println("Prior to type pattern matching:");
	for (Op op : ops) {
		if (op instanceof Add) {
			System.out.println(((Add)op).left + ((Add)op).right);
		}
		else if (op instanceof Subtract) {
			System.out.println(((Subtract)op).left - ((Subtract)op).right);
		}
		else if (op instanceof Multiply) {
			System.out.println(((Multiply)op).left * ((Multiply)op).right);
		}
		else if (op instanceof Divide) {
			System.out.println(((Divide)op).left / ((Divide)op).right);
		}
		else System.out.println("Dunno about " + op);
	}
	// end::oldway[]

	// tag::newway[]
	System.out.println("Using type pattern matching:");
	for (Op op : ops) {
		System.out.println(
			switch(op) {
			case null       -> "Null is not a valid Op!";
			case Add      a -> a.left + a.right;
			case Subtract s -> s.left - s.right;
			case Multiply m -> m.left * m.right;
			case Divide   d -> d.left / d.right;
			}
		);
	}
	// end::newway[]
}

List<Op> ops = List.of(
	new Add(2,2),
	new Subtract(7,5),
	new Multiply(3,7),
	new Divide(22, 7)
);
			

