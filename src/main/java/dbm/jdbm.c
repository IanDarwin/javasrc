#include <jni.h>
#include "DBM.h"	/* for our class information */
#include <stdio.h>
#include <dbm.h>	/* for native DBM library information */

#ident	"$Id$"

/*
 * This is the native implentation of our Java-DBM hookup.
 */
/*
 * Class:     DBM
 * Method:    dbminit
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_DBM_dbminit
  (JNIEnv *env, jobject this, jstring filename) {
	extern int errno;
	jboolean iscopy;
	jint ret = 0;
	const char *cname = (*env)->GetStringUTFChars(env, filename, &iscopy);
	printf("Filename is %s\n", cname);

	ret = dbminit(cname);

	/* if GetString made this copy for us, free it up. */
	if (iscopy) (*env)->ReleaseStringUTFChars(env, filename, cname);

	return 0-errno;
}

/*
 * Class:     DBM
 * Method:    dbmclose
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_DBM_dbmclose
  (JNIEnv *env, jobject this) {
}

/*
 * Class:     DBM
 * Method:    fetch
 * Signature: (Ljava/lang/Object;)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_DBM_fetch
  (JNIEnv *env, jobject this, jobject key) {
}

/*
 * Class:     DBM
 * Method:    store
 * Signature: (Ljava/lang/Object;Ljava/lang/Object;)I
 */
JNIEXPORT jint JNICALL Java_DBM_store
  (JNIEnv *env, jobject this, jobject key, jobject value) {
}

/*
 * Class:     DBM
 * Method:    delete
 * Signature: (Ljava/lang/Object;)I
 */
JNIEXPORT jint JNICALL Java_DBM_delete
  (JNIEnv *env, jobject this, jobject key) {
}

/*
 * Class:     DBM
 * Method:    firstkey
 * Signature: ()Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_DBM_firstkey
  (JNIEnv *env, jobject this) {
}

/*
 * Class:     DBM
 * Method:    nextkey
 * Signature: (Ljava/lang/Object;)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_DBM_nextkey
  (JNIEnv *env, jobject this, jobject key) {
}

