JAVACC?=	jikes +E
SPECIAL= TestForClass.java

all:	classes special
		$(JAVACC) $(SRCS)

classes:
		$(JAVACC) *.java

special:	../TestForClass.class

../TestForClass.class: TestForClass.java
		$(JAVACC) -d .. TestForClass.java
