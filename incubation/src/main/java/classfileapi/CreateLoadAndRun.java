package classfileapi;

import java.lang.classfile.*;
import java.nio.file.*;

/**
 * Create a canonical "Hello, World" class using ClassFiles API;
 * load it with a ClassLoader, and invoke methods via Reflection.
 * @author ClassFiles part based on example in javadoc for java.lang.classfiles
 * @author Glue around that by Ian Darwin
 */
// tag::main[]
public class CreateLoadAndRun {
	void main() {
		var CD_Hello = "notapackage.Hello";
		byte[] classData = ClassFile.of().build(CD_Hello,
			clb -> clb.withFlags(ClassFile.ACC_PUBLIC)
				// required no-argument constructor to be a valid class
				.withMethod(ConstantDescs.INIT_NAME, ConstantDescs.MTD_void,
						ClassFile.ACC_PUBLIC,
						mb -> mb.withCode(
								cob -> cob.aload(0)
										.invokespecial(ConstantDescs.CD_Object,
										  ConstantDescs.INIT_NAME, ConstantDescs.MTD_void)
										.return_()))
				// Standard "void main(String[])" main method
				.withMethod("main", MTD_void_StringArray, ClassFile.ACC_PUBLIC + ClassFile.ACC_STATIC,
						mb -> mb.withCode(
								cob -> cob.getstatic(CD_System, "out", CD_PrintStream)
											.ldc("Hello World")
											.invokevirtual(CD_PrintStream, "println", MTD_void_String)
											.return_())));

		Class<?> c = ClassLoader.getSystemClassLoader().defineClass("notapackage.Hello", classData, 0, classData.length);

		Method m = c.findMethod("main");

		m.invoke();
	}
}
// end::main[]
