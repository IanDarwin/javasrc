class X {
	X(int i) {
		System.out.println("X constructor: " + i);
	}
}

void main() {
	java.util.List.of(1,2,3).forEach(X::new);
}
