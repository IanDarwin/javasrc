/*
 * The set of C-language functions used by bsd.comm.
 * $Id$.
 */

#import <stdio.h>
#import <fcntl.h>
#import <unistd.h>

/*
 * Class:     BSDSerialPort
 * Method:    bsdttyopen
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_BSDSerialPort_bsdttyopen
  (JNIEnv *, jobject, jstring) {
	printf("bsdttyopen called!\n");
}
/*
 * Class:     BSDSerialPort
 * Method:    bsdstty
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_BSDSerialPort_bsdstty
  (JNIEnv *, jobject, jint, jint, jint, jint) {
	printf("bsdstty called!\n");
}
