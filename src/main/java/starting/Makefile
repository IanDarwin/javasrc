JAVACC?=	jikes +E
SRCS = Buggy.java Deprec.java EchoArgv.java Fetch.java FetchDebug.java \
	GetJavaVersion.java HelloWorld.java HelloWorldMocha.java IfDef.java \
	Person.java PersonTest.java TestFor1_1.java TestForSwing.java
SPECIAL= TestForClass.java

all:	classes special
		$(JAVACC) $(SRCS)

special:	../TestForClass.class

../TestForClass.class: TestForClass.java
		$(JAVACC) -d .. TestForClass.java
