rem
rem Build all the classes needed for the "server" directory
rem $Id$
rem
rem Clock doesn't get -deprecation as it has too many, sigh.
javac Clock.java
move Clock.class ..\server
javac -deprecation TreeLayout.java
move Tree*.class ..\server
javac -deprecation URLButton.java
move URLButton.class ..\server
cd webserver
javac -deprecation WebServer.java
move Handler.class ..\..\server
move WebServer.class ..\..\server
