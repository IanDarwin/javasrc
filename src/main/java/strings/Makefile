JAVACC?=	jikes +E

classes:
		$(JAVACC) *.java

tabtest:	EnTab.class
		java EnTab entab.txt | diff - entab.expect

# IF you have a C++ compiler and want to test my Java CSV against
# Kernighan & Pike's C++ original, you can invoke this rule.
csvtest:	csvgetline CSVFile.class
	csvgetline   < CSV.dat > j1
	java CSVFile < CSV.dat > j2
	-diff j1 j2

csvgetline: csvgetline.c++
	c++ csvgetline.c++ -lstdc++ -o $@

clean:
	rm -f *.class j1 j2 csvgetline

# This will have the side-effect of compiling all the other Java files.
entab.class:
		$(JAVACC) *.java
