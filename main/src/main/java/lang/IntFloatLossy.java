/// Conversion from int to float can be a lossy conversion, despite
/// that float's magnitude is higher - its precision isn't quite enough.
/// Moral: If in doubt, use double rather than float.
void main() {
	int i = Integer.MAX_VALUE - 123;
	System.out.println(i);
	float f = i;
	System.out.println(f);
	int j = (int)f;
	System.out.println(j);
	System.out.println(i == j ? "Not lossy" : "Lossy!");
}
