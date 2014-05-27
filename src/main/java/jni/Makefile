# Makefile for the 1.1 Java Native Methods examples for
# Java Cookbook, originally for Learning Tree International Course 471/478.
# Tested on Solaris both with "gcc" and with SunSoft "cc".
# Tested on OpenBSD with native port "devel/jdk/1.2" and cc.
# On other platforms it will certainly need some tweaking; please
# let me know how much! :-)
# Ian Darwin, http://www.darwinsys.com

# Configuration Section

CFLAGS_FOR_SO = -G # Solaris
CFLAGS_FOR_SO = -shared
CSRCS		= HelloJni.c
# JAVA_HOME should be been set in the environment
#INCLUDES	= -I$(JAVA_HOME)/include -I$(JAVAHOME)/include/solaris 
#INCLUDES	= -I$(JAVA_HOME)/include -I$(JAVAHOME)/include/openbsd
INCLUDES	= -I$(JAVA_HOME)/include

all:		testhello testjavafromc

# This part of the Makefile is for C called from Java, in HelloJni
testhello:		hello.all
		@echo
		@echo "Here we test the Java code \"HelloJni\" that calls C code."
		@echo
		LD_LIBRARY_PATH=`pwd`:. java HelloJni

hello.all:		HelloJni.class libhello.so

HelloJni.class: HelloJni.java
		javac HelloJni.java

HelloJni.h:	HelloJni.class
		javah -jni HelloJni

HelloJni.o::	HelloJni.h

libhello.so:	$(CSRCS) HelloJni.h
	$(CC) $(INCLUDES) $(CFLAGS_FOR_SO) $(CSRCS) -o libhello.so

# This part of the Makefile is for Java called from C, in javafromc
testjavafromc:	javafromc.all hello.all
	@echo
	@echo "Now we test HelloJni using javafromc instead of java"
	@echo
	./javafromc HelloJni
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
	rm -f core *.class *.o *.so HelloJni.h
clobber: clean
	rm -f javafromc
