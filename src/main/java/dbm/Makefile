# Makefile for the Java Native Methods in the DBM class.
# Ian Darwin, ian@darwinsys.com, http://www.darwinsys.com

# $Id$

# Configuration Section

CC			= cc
CC_SHARED	= -fpic
CLASS		= DBM.class
CSRCS		= DBM.c
HDR			= DBM.h
SHARED_UNIX	= dbm.so	# UNIX
SHARED_MSW32= dbm.dll	# MS-Win32
SHARED_OBJ	= $(SHARED_UNIX)

INCLUDES	= -I/usr/local/include/kaffe

all:		$(CLASS) $(SHARED_OBJ)

DBM.class: DBM.java
		javac DBM.java

#DBM.h:	DBM.class
#		javah -jni DBM

DBM.o::	DBM.h

$(SHARED_OBJ):	$(CSRCS) $(HDR)
	$(CC) $(INCLUDES) $(CC_SHARED) $(CSRCS) -o $(SHARED_OBJ)

clean:
	rm -f core *.class *.o *.so DBM.h
clobber: clean
