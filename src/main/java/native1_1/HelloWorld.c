#include <jni.h>
#include "HelloWorld.h"
#include <stdio.h>

void Java_HelloWorld_displayHelloWorld(JNIEnv *env, jobject this)
{
	jfieldID fldid;
	jint n, nn;

	(void)printf("Hello from a Native Method\n");

	if (this == NULL) {
		fprintf(stderr, "Input pointer is null!\n");
		return;
	}
	if ((fldid = (*env)->GetFieldID(env, this, "myNumber", "I")) == NULL) {
		fprintf(stderr, "GetFieldID failed");
		return;
	}
	n = (*env)->GetIntField(env, this, fldid);	/* retrieve myNumber */
	printf("Input value is %d\n", n);
	(*env)->SetIntField(env, this, fldid, ++n);	/* increment it! */
	nn = (*env)->GetIntField(env, this, fldid);
	printf("Input value now %d\n", nn); 		/* make sure */
	return;
}
