#include <jni.h>
#include "HelloWorld.h"
#include <stdio.h>
/*
 * This is the 1.1 implementation of displayHelloWorld.
 */
JNIEXPORT void JNICALL Java_HelloWorld_displayHelloWorld(JNIEnv *env, jobject this)
{
  jfieldID fldid;
  jint n, nn;

  (void)printf("Hello from a Native Method\n");

  if (this == NULL) {
    fprintf(stderr, "Input pointer is null!\n");
    return;
  }
  if ((fldid = (*env)->GetFieldID(env,
        (*env)->GetObjectClass(env, this), "myNumber", "I")) == NULL) {
    fprintf(stderr, "GetFieldID failed");
    return;
  }

  n = (*env)->GetIntField(env, this, fldid);/* retrieve myNumber */
  printf("\"myNumber\" value is %d\n", n);

  (*env)->SetIntField(env, this, fldid, ++n);/* increment it! */
  nn = (*env)->GetIntField(env, this, fldid);

  printf("\"myNumber\" value now %d\n", nn); /* make sure */
  return;
}
