# Makefile for the Java Native Methods in the DBM class.
# Ian Darwin, ian@darwinsys.com, http://www.darwinsys.com

# $Id$

# Configuration Section

CLASS		= DBM.class
CSRCS		= DBM.c
HDR			= DBM.h
SHARED_UNIX	= dbm.so	# UNIX
SHARED_MSW32= dbm.dll	# MS-Win32
SHARED_OBJ	= $(SHARED_UNIX)

JAVAHOME	= /usr/local/kaffe
INCLUDES	= -I$(JAVAHOME)/include -I$(JAVAHOME)/include

all:		$(CLASS) $(SHARED_OBJ)

DBM.class: DBM.java
		javac DBM.java

#DBM.h:	DBM.class
#		javah -jni DBM

DBM.o::	DBM.h

$(SHARED_OBJ):	$(CSRCS) $(HDR)
	$(CC) $(INCLUDES) -G $(CSRCS) -o $(SHARED_OBJ)

clean:
	rm -f core *.class *.o *.so DBM.h
clobber: clean
