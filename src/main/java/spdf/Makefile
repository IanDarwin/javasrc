#!/bin/sh
set -xe
jikes -d . *.java

jar cvf spdf.jar com &

java Demo > /tmp/demo.pdf
xpdf        /tmp/demo.pdf

