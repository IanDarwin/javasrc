# Makefile for the bsd.comm package.
# Ian Darwin, ian@darwinsys.com, http://www.darwinsys.com
# $Id$

# Configuration Section
CSRCS		= bsdcommio.c
SOBJS		= bsdcommio.o
SHLIB	= libbsdcommio.so

#JAVAC=		javac
JAVAC=		jikes +E

INCDIR		= /usr/local/include
INCLUDES	= -I$(INCDIR)/ -I$(INCDIR)/kaffe 
LIBDIR		= $(JAVAHOME)/lib
all:		testprog

# This part of the Makefile is for C called from Java
testprog:		prog.all
		@echo
		@echo "Here we test the Java code that calls C code."
		@echo
		java TestProg

prog.all:		TestProg.class $(SHLIB)

TestProg.class: TestProg.java
		$(JAVAC) TestProg.java
# The rule for javah -jni is not here, until Kaffe gets a javah.

$(SOBJS):	$(CSRCS) BSDSerialPort.h
	$(CC) $(INCLUDES) -c -fpic $(CSRCS)

$(SHLIB):	$(SOBJS) BSDSerialPort.h
	ld -Bshareable -o $(SHLIB) $(SOBJS)

clean:
	rm -f *core *.class *.o *.so
