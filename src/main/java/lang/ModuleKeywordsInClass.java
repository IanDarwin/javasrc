package lang;

/**
 * Show that it's OK to Use various "module-info" reserved words 
 * in a Java class source file.
 */
public class ModuleKeywordsInClass {
	public static void main(String[] args) {
		new ModuleKeywordsInClass().open();
	}
	void open() {
		var module = 42;
		System.out.println("Open method called: module# = " + module);
	}
}
