SUBDIR=	 
# Makefile for building files in The Java Cookbook (O'Reilly, 2001, Ian Darwin)
SHELL=	/bin/sh

# Pick a Java compiler. Any Java compiler that works.
#JAVAC=	javac
#JAVAC=	guavac
#JAVAC=	kaffe
JAVAC=	jikes +E

all:	checkpaths build

# Make sure the user has installed Java, and has my classes on their CLASSPATH
# Since at least one "javap" version stupidly returns 0 on failure, I use
# my own little TestForClass program here.
checkpaths:
		# Ensure java findable
		which java >/dev/null
		# Ensure com.darwinsys.util on CLASSPATH
		java TestForClass com.darwinsys.util.GetOpt || { \
			cat com-darwinsys-util.txt; exit 1; \
		}

# Then build everything.
build:
		for dir in ${SUBDIRS}; do ( cd $$dir; make "JAVACC=$(JAVACC)"); done

# Don't worry about (or try to use) this rule; it is only used by the book's
# author when adding a subdirectory to the list of files included.
subdirs:
		# Prevent end user from clobbering their Makefile
		grep javacook-src /cvs/CVSROOT/modules >/dev/null
		# get from "modules" the list of subdirectories, update Makefile
		DIR=`grep javacook-src /cvs/CVSROOT/modules | \
		sed -e 's/.*-a//' -e 's@javasrc/@@g'`; \
		for d in $$DIR; do if [ -d $$d ]; then LIST="$$LIST $$d"; fi; done; \
		(echo "SUBDIR=	$$DIR"; grep -v SUBDIR= Makefile) > Makefile.new; \
		mv Makefile.new Makefile
