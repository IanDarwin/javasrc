/*
 * The set of C-language functions used by bsd.comm.
 * $Id$.
 */

#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

#include "jni.h"
#include "BSDSerialPort.h"

/*
 * Class:     BSDSerialPort
 * Method:    bsdttyopen
 * Signature: (Ljava/lang/String;)I
 */
jint 
JNICALL Java_BSDSerialPort_bsdttyopen(JNIEnv *env, jobject this,
	jstring name)
{
	printf("bsdttyopen called!\n");
}
/*
 * Class:     BSDSerialPort
 * Method:    bsdstty
 * Signature: (IIII)I
 */
jint
JNICALL Java_BSDSerialPort_bsdstty(JNIEnv *env, jobject this,
	jint baud, jint databits, jint stopbits, jint parity)
{
	printf("bsdstty called!\n");
}
