JAVACC?=	jikes +E
SPECIAL= TestForClass.java

all:	classes special

classes:
		$(JAVAC) *.java

special:	../TestForClass.class

../TestForClass.class: TestForClass.java
		$(JAVAC) -d .. TestForClass.java

clean:
		@rm -f *.class
