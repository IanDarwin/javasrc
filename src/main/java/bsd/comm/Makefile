# Makefile for the bsd.comm package.

# Ian Darwin, ian@darwinsys.com, http://www.darwinsys.com

# $Id$

# Configuration Section

CSRCS		= bsdcommio.c
OBJS		= bsdcommio.o
JAVAHOME	= /usr/local/share/kaffe
INCDIR		= /usr/local/include
INCLUDES	= -I$(INCDIR)/ -I$(INCDIR)/kaffe 
LIBDIR		= $(JAVAHOME)/lib
TARGET_LIB		= libbsdcommio.so

all:		testprog

# This part of the Makefile is for C called from Java
testprog:		prog.all
		@echo
		@echo "Here we test the Java code that calls C code."
		@echo
		LD_LIBRARY_PATH=`pwd`:. java TestProg

prog.all:		TestProg.class $(TARGET_LIB)

TestProg.class: TestProg.java
		javac TestProg.java

# The rule for javah -jni is not here, until Kaffe gets a javah.

$(OBJS):	$(CSRCS) BSDSerialPort.h
	$(CC) $(INCLUDES) -c $(CSRCS) -o $(TARGET_LIB)

$(TARGET_LIB):	$(OBJS) BSDSerialPort.h
	$(CC) $(INCLUDES) -G $(OBJS) -o $(TARGET_LIB)

clean:
	rm -f *core *.class *.o *.so
