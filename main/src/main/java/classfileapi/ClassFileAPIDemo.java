package classfileapi;

import java.io.*;
import java.lang.annotation.*;
import java.lang.classfile.*;
import java.nio.file.*;

/**
 * A quick demo of the Java 22 PREVIEW "ClassFile" mechanism, described in
 * JEP 457 (https://openjdk.org/jeps/457).
 * N.B. Can only be run after "mvn compile" to create the target file
 */
// tag::main[]
@MyAnnotation
class ClassFileAPIDemo implements Serializable {
	int number = 0;
	void main() throws IOException {
		byte[] classFile = Files.readAllBytes(
			Path.of("target/classes/classfileapi/ClassFileAPIDemo.class"));
		ClassModel model = ClassFile.of().parse(classFile);
		for (ClassElement element : model) {
			switch (element) {
				case FieldModel field ->
					System.out.println(
					"Field " + field.fieldName().stringValue());
				case MethodModel method ->
					System.out.println(
					"Method " + method.methodName().stringValue());
				default -> { 
					/* Ignore anything else */
					System.out.println(
					"Other: " + element +
						"(" + element.getClass().getName() + ")");
				}
			}
		}
	}
}
// end::main[]

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
	int value = 42;
}
