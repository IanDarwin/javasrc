all:	files CDListClient.class CDListServer.class
files:	CDService.idl
	idltojava -fno-cpp CDService.idl
	javac CDService/*.java
	touch $@
CDListClient.class: CDListClient.java
	javac CDListClient.java
CDListServer.class: CDListServer.java
	javac CDListServer.java
