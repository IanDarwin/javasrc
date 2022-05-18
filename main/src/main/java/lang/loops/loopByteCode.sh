#!/bin/sh

set -x

javac -d . LoopFor.java
javap -c lang.loops.LoopFor > loopfor.txt

javac -d . LoopWhile.java
javap -c lang.loops.LoopWhile > loopwhile.txt

diff loop*.txt

rm -r lang *.txt
