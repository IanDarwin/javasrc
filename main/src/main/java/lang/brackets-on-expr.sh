#!/bin/sh

JAVAC=javac
#JAVAC=jikes

cd /tmp
mkdir bracketstest
cd    bracketstest

cat > t1.java <<!
public class t1 {
	public boolean test() {
		return (false);
	}
}
!

${JAVAC} t1.java

mv    t1.class t1with.class

cat > t1.java <<!
public class t1 {
	public boolean test() {
		return false;
	}
}
!

${JAVAC} t1.java

mv    t1.class t1without.class

ls -l t1*.class

cmp t1*.class && echo BINARY FILES ARE IDENTICAL
