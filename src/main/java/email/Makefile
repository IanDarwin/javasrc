# Makefile for email directory package
# $Id$

JAVAC?=	jikes +E

all:	checkpath classes

checkpath:
		@(cd ..; java TestForClass javax.mail.Message) || { \
			cat classpath.txt; exit 1; \
		}

classes:
		@echo "===> Building in `pwd`"
		$(JAVAC) *.java

clean:
		@echo Not removing classes due to TestOpenMailRelay program.
