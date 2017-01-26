= JavaSrcEE

Demos for Java EE.

This project is as old as the hills, and does contain some really old cruft.

DO NOT take this as best practices. About all I can say ATM is that it compiles,
and a few unit tests pass.

In particular, some subdirectories have their own deploy processes, which are
for ancient versions of Java EE servers (or, as they were called when this
was written, "J2EE" servers, a name that hasn't been in use since ~ 2005, BTW).

I make no pretense that any of this actually works, so USE AT OWN RISK.

== Note

In Java 9, they have taken pains to separate client code from server code.
The examples in at least jaxwsclient and jaxwsserver will need a bunch of
-addmods command-line args to compile and/or run; see 
http://mail.openjdk.java.net/pipermail/jdk9-dev/2016-May/004309.html
