# Makefile for textproc directory

CC?=	cc
JAVAC?=	jikes +E

all: test

test: Diff.class cacmdiff
	for n in 1 2; do \
		cacmdiff test$${n}a test$${n}b > j$${n}1; \
		java Diff test$${n}a test$${n}b > j$${n}2; \
		diff j$${n}1 j$${n}2 || echo Unexpected differences!!; \
	done

	cacmdiff Diff.java Diff.java
	java Diff Diff.java Diff.java

Diff.class:	Diff.java
	$(JAVAC) $?

cacmdiff:	diff.c
	$(CC) -o $@ $?
