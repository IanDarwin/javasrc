CSRCS		= HelloWorld.c
JAVAHOME	= /local/jdk1.1.2
INCLUDES	= -I$(JAVAHOME)/include -I$(JAVAHOME)/include/solaris 
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

libhello.so:	$(CSRCS)
	$(CC) $(INCLUDES) -G $(CSRCS) -o libhello.so

# This part of the Makefile is for Java called from C, in javafromc
testjavafromc:	javafromc.all hello.all
	@echo
	@echo "Now we test HelloWorld using javafromc instead of java"
	@echo
	LD_LIBRARY_PATH="$(LIBDIR):." CLASSPATH="$(CLASSPATH)" ./javafromc HelloWorld
	@echo
	@echo "That was, in case you didn't notice, C->Java->C. And,"
	@echo "incidentally, a replacement for JDK program \"java\" itself!"
	@echo


javafromc.all:	javafromc

javafromc:	javafromc.o
	$(CC) -L$(LIBDIR) javafromc.o -l java -o $@

javafromc.o:	javafromc.c
	$(CC) -c $(INCLUDES) javafromc.c

clean:
	rm -f core *.class *.o HelloWorld.h
clobber: clean
	rm -f javafromc
