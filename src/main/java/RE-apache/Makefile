all:		runtests

runtests:	Test.class tests REtest.class RE.class 
			java Test < tests
			java REtest

SE_CLASSES = SE.class SEany.class SEbol.class SEccl.class SEchar.class SEeol.class SErep.class
RE.class:	Int.class $(SE_CLASSES)

.include	<bsd.java.mk>
