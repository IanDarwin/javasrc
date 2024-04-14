# Makefile for the Java Native Methods examples for Java Cookbook,
# originally written for Learning Tree International # Course 471/478.
# Tested on Solaris both with "gcc" and with SunSoft "cc".
# Tested on OpenBSD with native port "devel/jdk" and cc.
# On other platforms it will certainly need some tweaking; please
# feel free to let me know how much! :-)
# Ian Darwin, https://darwinsys.com

# // tag::main[]

# Configuration Section

#CFLAGS_FOR_SO = -G # Solaris
CFLAGS_FOR_SO = -shared
CSRCS		= HelloJni.c
# JAVA_HOME should have been set in the environment
#INCLUDES	= -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/solaris 
#INCLUDES	= -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/openbsd
INCLUDES	= -I$(JAVA_HOME)/include -I/Library/Java/JavaVirtualMachines/jdk-22.jdk/Contents/Home/include/darwin/
LIBDIR=$(JAVA_HOME)/lib/server

all:		testhello testjavafromc

# This part of the Makefile is for C called from Java, in HelloJni
testhello:		hello.all
		@echo
		@echo "Here we test the Java code \"HelloJni\" that calls C code."
		@echo
		LD_LIBRARY_PATH=$(LIBDIR):. java HelloJni.java

hello.all:		HelloJni.class libhello.so

HelloJni.class: HelloJni.java
		javac -h . HelloJni.java

HelloJni.o::	jni_HelloJni.h

libhello.so:	$(CSRCS) jni_HelloJni.h
	$(CC) -L$(LIBDIR) $(INCLUDES) $(CFLAGS_FOR_SO) $(CSRCS) -o libhello.so

# This part of the Makefile is for Java called from C, in javafromc
testjavafromc:	javafromc.all hello.all
	@echo
	@echo "Now we test HelloJni using javafromc instead of java"
	@echo
	LD_LIBRARY_PATH=$(LIBDIR):. ./javafromc HelloJni
	@echo
	@echo "That was, in case you didn't notice, C->Java->C. And,"
	@echo "incidentally, a replacement for JDK program \"java\" itself!"
	@echo "(not a complete one, nor a very good one, but OK for simple cases)"
	@echo


javafromc.all:	javafromc

javafromc:	javafromc.o
	$(CC) -L$(LIBDIR) -L . javafromc.o -ljvm -o $@

javafromc.o:	javafromc.c
	$(CC) -c $(INCLUDES) javafromc.c

clean:
	rm -f core *.class *.o *.so jni_HelloJni.h
clobber: clean
	rm -f javafromc
# // end::main[]
