JAVACC?=	jikes +E
SPECIAL= TestForClass.java

all:	classes special

classes:
		$(JAVACC) *.java

special:	../TestForClass.class

../TestForClass.class: TestForClass.java
		$(JAVACC) -d .. TestForClass.java
