# Makefile for the 1.1 Java Native Methods examples for
# Java Cookbook, originally for Learning Tree International Course 471/478.
# Tested on Solaris both with "gcc" and with SunSoft "cc".
# Tested on OpenBSD with native port "devel/jdk/1.2" and cc.
# On other platforms it will certainly need some tweaking; please
# let me know how much! :-)
# Ian Darwin, http://www.darwinsys.com

# $Id$

# Configuration Section

CFLAGS_FOR_SO = -G # Solaris
CFLAGS_FOR_SO = -shared
CSRCS		= HelloWorld.c
#JAVAHOME	= /local/jdk1.1.2
#JAVAHOME	= /usr/local/jdk-1.2.2
JAVAHOME	= $$JAVA_HOME
#INCLUDES	= -I$(JAVAHOME)/include -I$(JAVAHOME)/include/solaris 
#INCLUDES	= -I$(JAVAHOME)/include -I$(JAVAHOME)/include/openbsd
INCLUDES	= -I$(JAVAHOME)/include
LIBDIR		= $(JAVAHOME)/lib/sparc/green_threads
CLASSPATH	= $(JAVAHOME)/lib/classes.zip:.

all:		testhello testjavafromc

# This part of the Makefile is for C called from Java, in HelloWorld
testhello:		hello.all
		@echo
		@echo "Here we test the Java code \"HelloWorld\" that calls C code."
		@echo
		LD_LIBRARY_PATH=`pwd`:. java HelloWorld

hello.all:		HelloWorld.class libhello.so

HelloWorld.class: HelloWorld.java
		javac HelloWorld.java

HelloWorld.h:	HelloWorld.class
		javah -jni HelloWorld

HelloWorld.o::	HelloWorld.h

libhello.so:	$(CSRCS) HelloWorld.h
	$(CC) $(INCLUDES) $(CFLAGS_FOR_SO) $(CSRCS) -o libhello.so

# This part of the Makefile is for Java called from C, in javafromc
testjavafromc:	javafromc.all hello.all
	@echo
	@echo "Now we test HelloWorld using javafromc instead of java"
	@echo
	LD_LIBRARY_PATH="$(LIBDIR):." ./javafromc HelloWorld
	@echo
	@echo "That was, in case you didn't notice, C->Java->C. And,"
	@echo "incidentally, a replacement for JDK program \"java\" itself!"
	@echo


javafromc.all:	javafromc

javafromc:	javafromc.o
	$(CC) -L$(LIBDIR) javafromc.o -ljava -o $@

javafromc.o:	javafromc.c
	$(CC) -c $(INCLUDES) javafromc.c

clean:
	rm -f core *.class *.o *.so HelloWorld.h
clobber: clean
	rm -f javafromc
