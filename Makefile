SHELL=	/bin/sh
SUBDIR=	

# Pick a Java compiler. Any Java compiler that works.
#JAVAC=	javac
#JAVAC=	guavac
#JAVAC=	kaffe
JAVAC=	jikes +E

all:	checkpath build

# Make sure the user has installed my classes on their CLASSPATH
checkpath:
		javap com.darwinsys.GetOpt || { \
			cat com.darwinsys-util.txt; exit 1 \
		}

# Then build everything.
build:
		for dir in ${SUBDIRS}; ( cd $$dir; make "JAVACC=${JAVACC}"); done

# Don't worry about this rule; it is only used by the book's author
# when adding a subdirectory to the list of files included.
subdirs:
		DIR=`grep javacook-src /cvs/CVSROOT/modules | \
		sed -e 's/.*-a//' -e 's@javasrc/@@g'`; \
		for d in $$DIR; do if [ -d $$d ]; then LIST="$$LIST $$d"; fi; done; \
		(grep -v SUBDIR= Makefile; echo "SUBDIR=	$$LIST") > Makefile.new && \
		mv Makefile.new Makefile
