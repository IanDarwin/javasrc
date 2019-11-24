#!/bin/sh
set -x
CLASSPATH=../build
java -classpath ${CLASSPATH} performance.TimeTimeable performance.PerfTimeString
java -classpath ${CLASSPATH} performance.TimeTimeable performance.PerfTimeRegex
java -classpath ${CLASSPATH} performance.TimeTimeable performance.PerfTimeString
java -classpath ${CLASSPATH} performance.TimeTimeable performance.PerfTimeRegex
