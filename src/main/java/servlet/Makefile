# Makefile for Servlet directory package
# $Id$

JAVAC?=	jikes +E

all:	checkpath classes

checkpath:
		@(cd ..; java TestForClass javax.servlet.http.HttpServlet) || { \
			cat classpath.txt; exit 1; \
		}

classes:
		@echo "===> Building in `pwd`"
		$(JAVAC) *.java

CodeRed:
	jikes -d $$HOME/webs/daroadweb/WEB-INF/classes CodeRed.java

clean:
		@rm -f *.class
