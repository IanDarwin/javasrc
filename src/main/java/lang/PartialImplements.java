// Can we implement part of an interface?

interface X {
	public void a();
	public void b();
}

abstract class A implements X {
	public void a() {}
}

class B extends A {
	public void b()  {}
}
