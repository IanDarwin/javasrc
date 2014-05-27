# Makefile for Ian Darwin's JNI-based LibSignal
# Ian Darwin, http://www.darwinsys.com/, http://www.darwinsys.com

# Configuration Section

CSRCS		= libsignal.c
JAVAHOME	= /local/jdk1.1
INCLUDES	= -I$(JAVAHOME)/include -I$(JAVAHOME)/include/solaris 
LIBDIR		= $(JAVAHOME)/lib/sparc/green_threads
CLASSPATH	= $(JAVAHOME)/lib/classes.zip:.

all:		LibSignal.html com/darwinsys/signal/LibSignal.class TestMain.class libsignal.so
		@echo ""
		@echo "Now we test it:"
		@echo ""
		-LD_LIBRARY_PATH=`pwd`:. java TestMain
		@cat test.dat	# doesn't work, do this by hand

LibSignal.html:	LibSignal.java
		javadoc LibSignal.java
		rm -f AllNames.html Package-LibSignal.html packages.html tree.html
		mv com.darwinsys.signal.LibSignal.html LibSignal.html

com/darwinsys/signal/LibSignal.class: LibSignal.java
		javac -d . LibSignal.java

TestMain.class: TestMain.java
		javac TestMain.java

com_darwinsys_signal_LibSignal.h: LibSignal.class
		javah -jni LibSignal

libsignal.so:	$(CSRCS) com_darwinsys_signal_LibSignal.h
		$(CC) -g $(INCLUDES) -G $(CSRCS) -o $@

clean:
	rm -f core *.class *.o *.so LibSignal.h

clobber: clean
		mv comp.darwinsys.signal.LibSignal.html LibSignal.html

dist:
		bundle LEGAL_NOTICE README Makefile LibSignal.html LibSignal.java \
			libsignal.c TestMain.java  > /tmp/signal.shar
