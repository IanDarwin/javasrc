JAVACC?=	jikes +E

entab:	EnTab.class
		java EnTab entab.txt | diff - entab.expect

csv:	csvgetline CSVFile.class
	csvgetline   < CSV.dat > j1
	java CSVFile < CSV.dat > j2
	-diff j1 j2

csvgetline: csvgetline.c++
	$(CPP) csvgetline.c++ -lstdc++ -o $@

clean:
	rm -f *.class j1 j2 csvgetline

# Copied from jmake.rules (include is importable among different makes)
SUFFIXES:	java class
#
JCFLAGS=
COMPILE.java=$(JAVAC) $(JCFLAGS)
#
# First rule so you can just "make x" where x.java --> x.class
.java:
	$(COMPILE.java) $<
# Second so you can explicitly say "make x.class" if you want.
.java.class:
	$(COMPILE.java) $<
