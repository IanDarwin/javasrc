# Makefile for web app.
DISTFILES=	legal-notice.txt Makefile README.txt TODO.txt \
	*.java *.jsp WEB-INF

servlets:
	jikes -d WEB-INF/classes *.java

war:
	jar cvf /tmp/seating.war  $(DISTFILES)
