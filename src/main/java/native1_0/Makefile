CSRCS		= HelloWorld.c HelloWorldImp.c
JAVAHOME	= /local/jdk1.1

all:		HelloWorld.class libhello.so test

test:		HelloWorld.class libhello.so
		LD_LIBRARY_PATH=`pwd` java HelloWorld

HelloWorld.class: HelloWorld.java
		javac HelloWorld.java

HelloWorld.c HelloWorld.h:	HelloWorld.class
		javah HelloWorld
		javah -stubs HelloWorld

libhello.so:	$(CSRCS)
	$(CC) -I$(JAVAHOME)/include -I$(JAVAHOME)/include/solaris -G $(CSRCS) -o libhello.so

clean:
	rm -f *.class HelloWorld.[ch]
