import java.lang.reflect.*;
import java.util.*;

public class FindField {
	public int y2kDate = 1999;
	public static void main(String[] unused) throws NoSuchFieldException,
			IllegalAccessException {
		FindField gf = new FindField();
		gf.process(gf);
	}

	void process(Object o) throws NoSuchFieldException, IllegalAccessException {
		Class c = o.getClass();
		Field fld = c.getField("y2kDate");
		System.out.println("Field's type = " + fld);
		int value = fld.getInt(o);
		System.out.println("Field's value = " + value);
	}
}

