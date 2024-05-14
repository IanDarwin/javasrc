package classfileapi;

import java.io.*;
import java.lang.classfile.*;
import java.lang.constant.*;
import java.lang.reflect.*;
import java.nio.file.*;

import static java.lang.constant.ConstantDescs.*;

/**
 * Create a canonical "Hello, World" class using ClassFiles API;
 * load it with a ClassLoader, and invoke main() via Reflection.
 * @author ClassFiles part based on example in javadoc 
 * for java.lang.classfiles
 * @author Glue around that by Ian Darwin
 */
// tag::main[]
public class CreateLoadAndRun extends ClassLoader {

	final String fullClassName = "notapackage.Hello";
	// A few ClassDescs that are not in ConstantDescs
	final ClassDesc CD_fullClassName = ClassDesc.of(fullClassName);
	final ClassDesc CD_System = ClassDesc.of(System.class.getName());
	final ClassDesc CD_PrintStream =
		ClassDesc.of(PrintStream.class.getName());
	// A few MethodDescs that are not in ConstantDescs
	final MethodTypeDesc MTD_void_String =
		MethodTypeDesc.of(CD_void, CD_String);
	final MethodTypeDesc MTD_void_StringArray =
		MethodTypeDesc.of(CD_void, CD_String.arrayType());

	void main() throws Exception {
		byte[] classData = ClassFile.of().build(CD_fullClassName,
			clb -> clb.withFlags(ClassFile.ACC_PUBLIC)

				// requires no-argument constructor to be a valid class
				.withMethod(ConstantDescs.INIT_NAME, ConstantDescs.MTD_void,
					ClassFile.ACC_PUBLIC,
					mb -> mb.withCode(
						cob -> cob.aload(0)
							.invokespecial(ConstantDescs.CD_Object,
							ConstantDescs.INIT_NAME, ConstantDescs.MTD_void)
							.return_()))

				// Standard "void main(String[])" main method
				.withMethod("main", MTD_void_StringArray,
					ClassFile.ACC_PUBLIC + ClassFile.ACC_STATIC,
					methodBuilder -> methodBuilder.withCode(
						cob -> cob.getstatic(CD_System, "out", CD_PrintStream)
							.ldc("Hello World")
							.invokevirtual(CD_PrintStream, "println", MTD_void_String)
							.return_())));

		// Save the new class to disk, just so we can examine & verify
		var dirPath = Path.of("/tmp/notapackage");
		if (!Files.exists(dirPath)) {
			Files.createDirectory(dirPath);
		}
		Files.write(Path.of("/tmp/notapackage/Hello.class"), classData);

		// Define the class in current ClassLoader (e.g., this) & call main()
		Class<?> c = 
			defineClass(fullClassName, classData, 0, classData.length);

		Method m = c.getMethod("main", String[].class);

		m.invoke(null, new Object[]{new String[0]});
	}
}
// end::main[]
