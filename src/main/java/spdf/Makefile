#!/bin/sh
set -xe
jikes -d . *.java
java Demo > /tmp/demo.pdf
xpdf        /tmp/demo.pdf
