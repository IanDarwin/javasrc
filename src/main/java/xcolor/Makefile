all:		java/awt/XColor.class

XColor.class: XColor.java
			javac XColor.java

install:	XColor.class
			cp XColor*.class /local/jdk1.1/classes/java/awt

doc:
			javadoc -authors -version XColor.java
			rm -f All*.html packages.html tree.html
