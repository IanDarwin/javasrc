// Just some notes...

public class JavaScan {
	// returns an array of tokens
}

public class Java2RTF extends JavaScan # or implements it, make inteface
{
	// read tokens, emit bold around keywords

	Hashtable keyHash = new Hashtable();

	// put the java Keywords into it

	boolean isKeyword(String name) {
		return keyHash.get(name) != null;
	}
}
