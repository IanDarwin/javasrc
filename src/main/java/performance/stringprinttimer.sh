#!/bin/ksh

# Time the StringPrint programs a few times.

for f in A AA B A AA B 
do
	echo -n $f
	time java StringPrint$f >/dev/null
done
