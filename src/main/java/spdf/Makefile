spdf.jar:
	jikes -d . *.java
	jar cvf spdf.jar com

test:
	java Demo > /tmp/demo.pdf
	xpdf        /tmp/demo.pdf

clean:
	-rm -rf com *.class

