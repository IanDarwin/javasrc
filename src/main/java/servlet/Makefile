# Makefile for Servlet directory package
# $Id$

JAVACC?=	jikes +E

all:	checkpath classes

checkpath:
		@(cd ..; java TestForClass javax.servlet.http.HttpServlet) || { \
			cat classpath.txt; exit 1; \
		}

classes:
		@echo "===> Building in `pwd`"
		$(JAVACC) *.java

clean:
		@rm -f *.class
