# Makefile for javax.comm package
# $Id$

JAVACC?=	jikes +E

all:	checkpath classes

checkpath:
		@(cd ..; java TestForClass javax.comm.CommPortOpen) || { \
			cat javax.comm.txt; exit 1; \
		}

classes:
		@echo "===> Building in `pwd`"
		$(JAVACC) *.java

clean:
		rm -f *.class
