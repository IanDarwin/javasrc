all:		runtests

runtests:	Test.class tests RE.class 
			java Test < mytests
retests:	REtest.class
			java REtest

# On Kaffe, need to compile RE.java and SE*.java due to circularities
# javac and most other java compilers are Ok with this.
RE.class:	Int.class Match.class
			javac RE.java SE*.java

.include	<bsd.java.mk>
