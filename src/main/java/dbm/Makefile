# Makefile for the Java Native Methods in the DBM class.
# Ian Darwin, ian@darwinsys.com, http://www.darwinsys.com

# $Id$

# Configuration Section

CC			= cc
CC_SHARED	= -fpic
LD_SHARED	= -shared
CLASS		= DBM.class
CSRCS		= jdbm.c
OBJS		= jdbm.o
HDR			= DBM.h
SHARED_UNIX	= libjdbm.so	# UNIX
SHARED_MSW32= jdbm.dll	# MS-Win32
SHARED_OBJ	= $(SHARED_UNIX)

INCLUDES	= -I/usr/local/include/kaffe

test:		Test.class main testdb
			java Test
testdb:		/tmp/mydb.dir /tmp/mydb.pag
/tmp/mydb.dir /tmp/mydb.pag:
			touch /tmp/mydb.dir /tmp/mydb.pag

main:		$(CLASS) $(SHARED_OBJ)

DBM.class: DBM.java
		javac DBM.java

#DBM.h:	DBM.class
#		javah -jni DBM

jdbm.o:	jdbm.c DBM.h
	$(CC) -c $(INCLUDES) $(CSRCS) 

$(SHARED_OBJ):	$(OBJS) $(HDR)
	$(LD) $(LD_SHARED) $(OBJS) -o $(SHARED_OBJ)

clean:
	rm -f core *.class *.o *.so DBM.h
clobber: clean
