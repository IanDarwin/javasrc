package lang;

// Can we implement part of an interface?

interface X {
	public void a();
	public void b();
}

abstract class AA implements X {
	public void a() {}
}

class BB extends AA {
	public void b()  {}
}
