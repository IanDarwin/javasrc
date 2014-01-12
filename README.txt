JavaCook Source Files

Please note the following changes since the Second Edition of the
Java Cookbook was published:

*) This is now being built for Java 7 & 8. Sorry if you are on some relic
    platform that doesn't have Java 7 or some environment in which Java 7 is
    not yet "approved", but the world has moved on; Java 6 is a relic, is
    deprecated, and has been EOL'd by Oracle. I am updating the Cookbook
    for Java 8 as you read this; Java 7 is now the minimum. You can probably
    get by with a huge excludes list, but you're on your own if you do that.

	In fact, you'll soon need an Excludes list if you want to run with Java 7.
	Sorry, but the Java world keeps moving so the target has to keep moving.

*)	The Java files are now in Java packages whose name corresponds 
	to the directory they are in (this is how most tools including
	Ant and Eclipse expect to find things). They are further in the
	subdirectory "src/main/java" which is where most *modern* tools
	including Maven, Gradle, and others, expect them to be.
	
*)	The index-bychapter file is substantially out of date since the
	packagification "flag day".

*)	I am using Eclipse for most of my development. And Maven for
	building. Thus any Ant scripts you find are out of date and unsupported;
	if you get them working please send me a pull request against the git repo.

*)	An unspecified number of files here are used in the Java Cookbook;
	those that have been converted to the newest publishing software at
	O'Reilly will have "// BEGIN" and "// END" comments to mark sections for
	mechanical inclusion into the book. These are just comments and can be
	completely ignored by people looking at the source code normally.

Ian Darwin
Java Cookbook author
http://www.darwinsys.com/contact.jsp

