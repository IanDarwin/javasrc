all:
	jikes +E -d WEB-INF/classes BuzzInServlet.java
war:	/tmp/buzzin.war
/tmp/buzzin.war:
	jar cvf /tmp/buzzin.war .
dist:
	cd /tmp; ln -s /usr/download/java/*.exe .

