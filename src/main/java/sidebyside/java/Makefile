all:	run
run:	HelloWorld.class
	java sidebyside.java.HelloWorld
HelloWorld.class:	HelloWorld.java
	javac -d . HelloWorld.java
clean:
	@rm -rf *.class sidebyside
