package classfileapi;

import java.lang.classfile.*;
import java.nio.file.*;

/**
 * A quick demo of the Java 22 PREVIEW "ClassFile" mechanism, described in
 * JEP 457 (https://openjdk.org/jeps/457).
 */
// tag::main[]
class ClassFileAPIDemo {
	void main() {
		byte[] classFile = Files.readAllBytes(Path.of("demo.class"));
		ClassModel model = ClassFile.of().parse(bytes);
		for (ClassElement element : model) {
			switch (element) {
				case AnnotationModel annote ->
					 System.out.println(STR."Annotation \{annote.annotationName.stringValue()}");
				case FieldModel field ->
					System.out.println(STR."Field \{field.fieldName().stringValue()}");
				case MethodModel method ->
					 System.out.println(STR."Method \{method.methodName().stringValue()}");
				default -> { /* Ignore anything else */ }
			}
		}
	}
}
// end::main[]
