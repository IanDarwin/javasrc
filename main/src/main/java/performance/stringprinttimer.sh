#!/bin/ksh

# Time the StringPrint programs a few times.

# Direct standard output to bit bucket, since they print a LOT;
# Time programs are usually smart enough to report on another file
# like standard error, for this reason

javac -d . TimeNoArgs.java StringPrint*.java
java -cp . performance.TimeNoArgs \
	performance.StringPrintA performance.StringPrintAA performance.StringPrintB \
	performance.StringPrintA performance.StringPrintAA performance.StringPrintB   > /dev/null

rm -r ./performance # clean up
