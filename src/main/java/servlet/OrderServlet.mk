set -x

JEEVES_HOME=/local/JavaServerBeta

javac -classpath /local/java/lib/classes.zip:$(JEEVES_HOME)/lib/classes.zip \
	OrderServlet.java &&

	cp OrderServlet.class $(JEEVES_HOME)/servlets/ &&

	jeeves stop && jeeves start
