void foo(Long l) {
	System.out.println("In object variant");
}

void foo(long l) {
	System.out.println("In primitive variant");
}

void main() {
	foo(1L);
	Long l = 1L;
	foo(l);
}

