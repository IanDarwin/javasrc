CSRCS		= HelloWorld.c
JAVAHOME	= /local/jdk1.1.2
INCLUDES	= -I$(JAVAHOME)/include -I$(JAVAHOME)/include/solaris 
LIBDIR		= $(JAVAHOME)/lib/sparc/green_threads
CLASSPATH	= $(JAVAHOME)/lib/classes.zip:.


all:		testhello testjavafromc

# This part of the Makefile is for C called from Java, in HelloWorld
testhello:		hello.all
		LD_LIBRARY_PATH=`pwd`:. java HelloWorld

hello.all:		HelloWorld.class libhello.so

HelloWorld.class: HelloWorld.java
		javac HelloWorld.java

HelloWorld.h:	HelloWorld.class
		javah -jni HelloWorld

libhello.so:	$(CSRCS)
	$(CC) $(INCLUDES) -G $(CSRCS) -o libhello.so

# This part of the Makefile is for Java called from C, in javafromc
testjavafromc:	javafromc.all hello.all
	LD_LIBRARY_PATH="$(LIBDIR)" CLASSPATH="$(CLASSPATH)" ./javafromc HelloWorld

javafromc.all:	javafromc

javafromc:	javafromc.o
	$(CC) -L$(LIBDIR) javafromc.o -l java -o $@

javafromc.o:	javafromc.c
	$(CC) -c $(INCLUDES) javafromc.c

clean:
	rm -f *.class # HelloWorld.[ch]
