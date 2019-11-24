#include <jni.h>
#include "DBM.h"	/* current directory - for our class information */
#include <stdio.h>
#include <stdlib.h>
#include <dbm.h>	/* system directory - for UNIX DBM library information */
#include <string.h>	/* needed for strerror */

#ident	"$Id$"

extern int errno;

/*
 * This is the native implentation of our Java-DBM hookup.
 */

/* Convenience routine: make datum into jbyteArray */
jbyteArray datumToByteArray(JNIEnv *env, datum d) {

	jbyteArray value = (*env)->NewByteArray(env, d.dsize);
	(*env)->SetByteArrayRegion(env, value, 0, d.dsize, d.dptr);
	return value;
}

/* Convenience routine: make jbyteArray into datum */
datum byteArrayToDatum(JNIEnv *env, jbyteArray ba) {
	datum d;
	d.dsize = (*env)->GetArrayLength(env, ba);
	d.dptr = malloc(d.dsize);
	(*env)->GetByteArrayRegion(env, ba, 0, d.dsize, d.dptr);
	return d;
}

/*
 * Method:    dbminit
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_DBM_dbminit
  (JNIEnv *env, jobject this, jstring filename) {
	jboolean iscopy;
	jint ret = 0;
	int err;
	const char *cname = (*env)->GetStringUTFChars(env, filename, &iscopy);
	// printf("Filename is %s\n", cname);

	ret = dbminit(cname);
	err = errno;

	/* if GetString made this copy for us, free it up. */
	if (iscopy)
		(*env)->ReleaseStringUTFChars(env, filename, cname);

	// If dbminit() failed, throw exception containing strerror(errno)
	if (ret < 0) {
		jclass myClass = (*env)->FindClass(env,
			"java/lang/IllegalArgumentException");
		(*env)->ThrowNew(env, myClass, strerror(err));
	}

	return 0;
}

/*
 * Method:    dbmclose
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_DBM_dbmclose
  (JNIEnv *env, jobject this) {

	int ret = dbmclose();
	int err = errno;

	// If dbm failed, throw exception containing strerror(errno)
	if (ret < 0) {
		jclass myClass = (*env)->FindClass(env,
			"java/lang/IllegalArgumentException");
		(*env)->ThrowNew(env, myClass, strerror(err));
	}

	return 0;
}

/*
 * Method:    dbmstore
 * Signature: ([B[B)I
 */
JNIEXPORT jint JNICALL Java_DBM_dbmstore
  (JNIEnv *env, jobject this, jbyteArray key, jbyteArray value){
	datum k, v;

	// Create the C-language "datum" for "key"
	k.dsize = (*env)->GetArrayLength(env, key);
	k.dptr = malloc(k.dsize);			// XXX
	(*env)->GetByteArrayRegion(env, key, 0, k.dsize, k.dptr);

	// Create the C-language "datum" for "value"
	v.dsize = (*env)->GetArrayLength(env, value);
	v.dptr = malloc(v.dsize);			// XXX
	(*env)->GetByteArrayRegion(env, value, 0, v.dsize, v.dptr);

	// printf("In store, keylen %d, vallen %d\n", k.dsize, v.dsize);

	// Call dbm store(); if it fails, throw exception containing strerror(errno)
	if (store(k, v) < 0) {
		int err = errno;
		jclass myClass = (*env)->FindClass(env,
			"java/lang/IllegalArgumentException");
		(*env)->ThrowNew(env, myClass, strerror(err));
	}
	return 0;
}

/*
 * Method:    dbmfetch
 * Signature: ([B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_DBM_dbmfetch
  (JNIEnv *env, jobject this, jbyteArray key) {
	datum k, v;

	// Create the C-language "datum" for "key"
	k.dsize = (*env)->GetArrayLength(env, key);
	k.dptr = malloc(k.dsize);			// XXX
	(*env)->GetByteArrayRegion(env, key, 0, k.dsize, k.dptr);

	v = fetch(k);
	if (v.dptr == NULL) {
		printf("fetch didn't find it\n");
		return NULL;
	}

	return datumToByteArray(env, v);
}

/*
 * Method:    delete
 * Signature: (Ljava/lang/Object;)I
 */
JNIEXPORT jint JNICALL Java_DBM_delete
  (JNIEnv *env, jobject this, jobject key) {
	return 0;
}

/*
 * Method:    firstkey
 * Signature: ()[B
 */
JNIEXPORT jbyteArray JNICALL Java_DBM_firstkey
  (JNIEnv *env, jobject this) {
	datum d = firstkey();
	if (d.dptr == NULL)
		return NULL;
	return datumToByteArray(env, d);
}

/*
 * Method:    nextkey
 * Signature: ([B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_DBM_nextkey
  (JNIEnv *env, jobject this, jbyteArray key) {
	datum d = nextkey(byteArrayToDatum(env, key));
	if (d.dptr == NULL)
		return NULL;
	// printf("Java_DBM_nextkey, d.dptr=%x, d.dsize=%d\n", d.dptr, d.dsize);
	return datumToByteArray(env, d);
}

