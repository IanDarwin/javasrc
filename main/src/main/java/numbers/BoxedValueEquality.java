/**
 * Let's see if all boxed Integers work the same as all others.
 */
void main() {
	Integer i1 = Integer.valueOf(3);
	Integer i2 = Integer.valueOf(3);
	var equality1 = i1 == i2;
	System.out.println(equality1);
	i1 = Integer.valueOf(4242);
	i2 = Integer.valueOf(4242);
	var equality2 = i1 == i2;
	System.out.println(equality2);

	if (equality1 != equality2) {
		System.out.println("""
			Something happening here.
			What it is ain't exactly clear!
		""");
	} else {
		System.out.println("Relax, Dave. It's all working");
	}
}


