CLASSES= Hello.class HelloEJB.class HelloHome.class

hello.jar:	$(CLASSES)
	jar cvf hello.jar META-INF $(CLASSES)

install:	hello.jar
	cp hello.jar /home/ian/jboss/jboss/deploy
