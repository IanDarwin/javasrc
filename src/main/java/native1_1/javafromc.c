#include <stdio.h>
#include <jni.h>
	
int
main(int argc, char *argv[])
{
	JavaVM *jvm;       /* The Java VM we will use */
	JNIEnv *myEnv;       /* pointer to native environment */
	JDK1_1InitArgs jvmArgs; /* JNI initialization arguments */
	jclass myClass;		/* pointer to the class type */
	jmethodID myMethod;		/* pointer to the main() method */
	
	JNI_GetDefaultJavaVMInitArgs(&jvmArgs);	/* set up the argument pointer */
	/* Could change values now, like: jvmArgs.classpath = ...; */
	
	/* initialize the JVM! */
	if (JNI_CreateJavaVM(&jvm, &myEnv, &jvmArgs) < 0) {
		fprintf(stderr, "CreateJVM failed\n");
		exit(1);
	}
	
	/* find the class named in argv[1] */
	if ((myClass = (*myEnv)->FindClass(myEnv, argv[1])) == NULL) {
		fprintf(stderr, "FindClass %s failed\n", argv[1]);
		exit(1);
	}

	/* find the static void main(String[])  method of that class */
	/* myMethod = (*myEnv)->GetStaticMethodID(myEnv, myClass, "main", "([L/java/lang/String;)V"); */
	myMethod = (*myEnv)->GetMethodID(myEnv, myClass, "test", "(I)I");
	if (myMethod == NULL) {
		fprintf(stderr, "GetStaticMethodID failed\n");
		exit(1);
	}
	/* finally, call the method */
	(*myEnv)->CallStaticVoidMethod(myEnv, myClass, myMethod, 100);
	
	(*jvm)->DestroyJavaVM(jvm);	/* no error checking as we're done anyhow */

	return 0;
}
