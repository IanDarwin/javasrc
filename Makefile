SUBDIR=	 starting environ strings RE numbers datetime structure oo Plotter io tar textproc javacomm dir_file graphics gui i18n network netweb servlet_jsp DBM JDBC email xml rmi packaging beans threads introspection otherlang native1.1 template

# Makefile for building files in The Java Cookbook (O'Reilly, 2001, Ian Darwin)
# $Id$

# Pick a Java compiler. Any Java compiler that works.
#JAVAC=	javac
#JAVAC=	guavac
#JAVAC=	kaffe
JAVACC=	jikes +E
# Make sure the user picked one.
JAVACC?= javac

SHELL=	/bin/sh

# There are three types of Makefiles in SUBDIR/*. Most of them
# are not in CVS, but are copied from Makefile.simple by "make makefiles"
# The few listed there:
SUBDIRS_WITH_ERROR_DEMOS= introspection language numbers netweb
# are similarly copied by "make makefiles" from "Makefile.exclude-errors";
# these directories have files (discussed in the book) that show
# why certain features won't compile without change.
# The third type are hand-maintained; these are for those that
# either need a "checkpath" rule similar to the one in this Makefile,
# or have other special needs.

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
		@for dir in $(SUBDIR); do ( cd $$dir; make -k "JAVACC=$(JAVACC)"); done

clean:
		@for dir in $(SUBDIR); do echo "===> cleaning in $$dir"; \
			( cd $$dir; make clean ); done

# For any subdirectory that doesn't already have a Makefile, create a simple one
makefiles:
		@for dir in $(SUBDIRS_WITH_ERROR_DEMOS); do \
			echo "===> $$dir/Makefile (exclude-errors)"; \
			cp Makefile.exclude-errors $$dir/Makefile; \
		done
		@for dir in $(SUBDIR); do if [ ! -f $$dir/Makefile ]; then \
			echo "===> $$dir/Makefile"; \
			cp Makefile.simple $$dir/Makefile; \
		fi; done
# Get rid of copied Makefiles
makefiles.clean:
		@for dir in $(SUBDIR); do \
		if cmp -s Makefile.simple $$dir/Makefile; then \
			echo "===> rm $$dir/Makefile"; \
			rm $$dir/Makefile; \
		elif cmp -s Makefile.exclude-errors $$dir/Makefile; then \
			echo "===> rm $$dir/Makefile"; \
			rm $$dir/Makefile; \
		fi; \
		done

# Don't worry about (or try to use) this rule; it is only used by the book's
# author when adding a subdirectory to the list of files included.
subdirs:
		# Prevent end user from clobbering their Makefile
		grep javacook-src /cvs/CVSROOT/modules >/dev/null
		# get from "modules" the list of subdirectories, update Makefile
		DIR=`grep javacook-src /cvs/CVSROOT/modules | \
		sed -e 's/.*-a//' -e 's@javasrc/@@g'`; \
		for d in $$DIR; do if [ -d $$d ]; then LIST="$$LIST $$d"; fi; done; \
		(echo "SUBDIR=	$$LIST"; grep -v '^SUBDIR=' Makefile) > Makefile.new; \
		mv Makefile.new Makefile
