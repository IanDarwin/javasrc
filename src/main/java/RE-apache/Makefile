all:		runtests programs

runtests:	Test.class tests RE.class 
			java Test < mytests
retests:	REtest.class
			java REtest

# On Kaffe, need to compile RE.java and SE*.java due to circularities
# javac and most other java compilers are Ok with this.
RE.class:	Int.class Match.class
			javac RE.java SE*.java

programs:	Grep1.class

Grep1.class: RE.class

.include	<bsd.java.mk>
