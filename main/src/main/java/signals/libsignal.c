/*
 * This is the 1.1 implentation of Ian Darwin`s libSignal,
 * a Java Native (JNI) function for catching a single signal.
 * Mainly useful for cleanup.
 */

#include <stdio.h>
#include <signal.h>
#include <jni.h>
#include "com_darwinsys_signal_LibSignal.h"

#ident	"$Id$"

static JNIEnv *myEnv;	/* the Java runtime environment */
static jobject tClass;	/* the Class for the Thread. */
static jobject jThread;	/* the Thread whose start() we will call. */
jmethodID rMethod;		/* pointer to the Thread's run() method itself */
static void sigHandler(int signum, siginfo_t *info, void *data);

/*
 * Class:     com_darwinsys_signal_LibSignal
 * Method:    setsighandler
 * Signature: (Ljava/lang/Thread;)V
 */
JNIEXPORT void JNICALL
Java_com_darwinsys_signal_LibSignal_setsighandler(JNIEnv *env,
	jclass this,
	jobject thread)
{
	struct sigaction newact;
	int ret;

	(void)printf("Hello from Native setSignalHandler\n");

	if (this == NULL) {
		fprintf(stderr, "\"this.\" pointer is null!\n");
		return;
	}
	if (thread == NULL) {
		fprintf(stderr, "\"thread\" pointer is null!\n");
		return;
	}

	myEnv = env;		/* save for handler */
	jThread = thread;		/* ditto */

	/* Need the thread's Class for GetMethodID */
	tClass = (*myEnv)->GetObjectClass(myEnv, thread);
	if (tClass == NULL) {
		fprintf(stderr, "GetObjectClass failed\n");
		exit(1);
	}

	/* find and save ID for the start() method of the given class */
	rMethod = (*myEnv)->GetMethodID(myEnv, tClass, "start", "()V");
	if (rMethod == NULL) {
		fprintf(stderr, "GetMethodID failed\n");
		exit(1);
	}
	printf("rMethod=%08x\n", rMethod);

	/* NOW SET THE SIGNAL */
	newact.sa_sigaction = sigHandler, 
	newact.sa_flags = SA_SIGINFO;
	ret = sigaction(SIGINT, &newact, NULL);
}

/*
 * This is C code that calls the runnable's run() method when
 * the signal comes along.
 */
static void
sigHandler(int signum, siginfo_t *info, void *data)
{
	int i;
	jthrowable tossed;	/* Exception object, if we get one. */

	printf("Signal handler for signum %d at %08x\n", signum, sigHandler);

	/* finally, call the method. */
	printf("Calling native method:\n");
	(*myEnv)->CallVoidMethod(myEnv, jThread, rMethod, NULL);

	/* And, if we get control back at all check for exceptions */
	if ((tossed = (*myEnv)->ExceptionOccurred(myEnv)) != NULL) {
		fprintf(stderr, "LibSignal: Exception detected:\n");
		(*myEnv)->ExceptionDescribe(myEnv);	/* writes on stderr */
		(*myEnv)->ExceptionClear(myEnv);	/* OK, we're done with it. */
	}
}
