#!/bin/ksh

# Time the StringPrint programs a few times.

# Direct standard output to bit bucket, since they print a LOT;
# Time programs are usually smart enough to report on another file
# like standard error, for this reason

jr TimeNoArgs \
	StringPrintA StringPrintAA StringPrintB \
	StringPrintA StringPrintAA StringPrintB   > /dev/null
