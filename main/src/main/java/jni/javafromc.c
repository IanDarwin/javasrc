// tag::main[]
/*
 * This is a C program that calls Java code.
 * This could be used as a model for building Java into an
 * existing application as an extention language, for example.
 */

#include <stdio.h>
#include <stdlib.h>
#include <jni.h>

int
main(int argc, char *argv[]) {
	int i;
	JavaVM *jvm;		/* The Java VM we will use */
	JNIEnv *myEnv;		/* pointer to native environment */
	JavaVMInitArgs jvmArgs; /* JNI initialization arguments */
	jclass myClass, stringClass;	/* pointer to the class type */
	jmethodID myMethod;	/* pointer to the main() method */
	jarray args;		/* becomes an array of Strings */
	jthrowable tossed;	/* Exception object, if we get one. */
	
	/* initialize the JVM! */
	JavaVMOption options[2];
	options[0].optionString = "-Xmx512m";
	options[1].optionString = "-XX:NonNMethodCodeHeapSize=128m";
	jvmArgs.version = JNI_VERSION_1_6;
	jvmArgs.nOptions = 1;
	jvmArgs.options = options;
	jvmArgs.ignoreUnrecognized = 1;

	if (JNI_CreateJavaVM(&jvm, (void **)&myEnv, &jvmArgs) < 0) {
		fprintf(stderr, "CreateJVM failed\n");
		exit(1);
	}
	
	/* find the class named in argv[1] */
	if ((myClass = (*myEnv)->FindClass(myEnv, argv[1])) == NULL) {
		fprintf(stderr, "FindClass %s failed\n", argv[1]);
		exit(1);
	}

	/* find the static void main(String[]) method of that class */
	myMethod = (*myEnv)->GetStaticMethodID(
		myEnv, myClass, "main", "([Ljava/lang/String;)V");
	if (myMethod == NULL) {
		fprintf(stderr, "GetStaticMethodID failed\n");
		exit(1);
	}

	/* Since we're calling main, must pass along the command line arguments,
	 * in the form of Java String array
	 */
	if ((stringClass = (*myEnv)->FindClass(myEnv, "java/lang/String")) == NULL){
		fprintf(stderr, "get of String class failed!!\n");
		exit(1);
	}

	/* make an array of Strings, subtracting 1 for progname & 1 for the
	 * java class name */
	if ((args = (*myEnv)->
			NewObjectArray(myEnv, argc-2, stringClass, NULL))==NULL) {
		fprintf(stderr, "Create array failed!\n");
		exit(1);
	}

	/* fill the array */
	for (i=2; i<argc; i++)
		(*myEnv)->SetObjectArrayElement(myEnv,
			args, i-2, (*myEnv)->NewStringUTF(myEnv, argv[i]));

	/* finally, call the method. */
	(*myEnv)->CallStaticVoidMethodA(myEnv, myClass,
		myMethod, (const jvalue *)&args);

	/* And check for exceptions */
	if ((tossed = (*myEnv)->ExceptionOccurred(myEnv)) != NULL) {
		fprintf(stderr, "%s: Exception detected:\n", argv[0]);
		(*myEnv)->ExceptionDescribe(myEnv);	/* writes on stderr */
		(*myEnv)->ExceptionClear(myEnv);	/* We're done with it. */
	}
	
	(*jvm)->DestroyJavaVM(jvm); /* no error checking as we're done anyhow */
	return 0;
}
// end::main[]
