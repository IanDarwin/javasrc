package nio;

// tag main[]
import java.nio.file.Path;

public class PathDemo {

	public static void main(String[] args) { 
		var p = Path.of("/etc/hosts");
		display("Path", p);
		display("isAbsolute", p.isAbsolute());
		display("getRoot", p.getRoot());
		display("getParent", p.getParent());
		var q = Path.of("PathDemo.java");
		display("endsWith(.php)", q.endsWith(".php"));
		display("endsWith(.java)", q.endsWith(".java"));
		var s = Path.of("/usr/bin/../../home/ian/../../etc/hosts");
		display("Normalize long path", s = s.normalize());
		display("Equals after normalize", s.equals(p));
		var t = Path.of("Foo.java");
		display("absolutePath", t.toAbsolutePath());
	}

	// end main[]
	/** Convenience routine */
	static void display(String msg, String value) {
		System.out.printf("%25s: %s\n", msg, value);
	}
	static void display(String msg, Object value) {
		System.out.printf("%25s: %s\n", msg, value.toString());
	}
}
