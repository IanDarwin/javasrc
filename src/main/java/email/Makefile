# Makefile for Servlet directory package
# $Id$

JAVACC?=	jikes +E

all:	checkpath classes

checkpath:
		@(cd ..; java TestForClass javax.mail.Message) || { \
			cat classpath.txt; exit 1; \
		}

classes:
		@echo "===> Building in `pwd`"
		$(JAVACC) *.java
