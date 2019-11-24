package reflection;

import java.lang.reflect.Field;

public class GetPrivateClassPart2 {

	public static void discover(Object o) {
		Class<? extends Object> c = o.getClass();
		System.out.println(c);
		Field[] fields = c.getFields();
		for (Field f : fields) {
			System.out.println(f);
		}
	}

}
