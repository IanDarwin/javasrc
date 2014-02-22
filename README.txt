= JavaCook Source Files (javasrc)

* You MUST HAVE Java 8 to successfully compile this whole package.*

. This is now being built for Java 7 & 8. Sorry if you are on some relic
platform that doesn't have Java 7 or some environment in which Java 7 is
not yet "approved", but the world has moved on; Java 6 is a relic, is
deprecated, and has been EOL'd by Oracle. I am updating the Cookbook
for Java 8 as you read this; Java 7 is now the minimum. You can probably
get by with a huge excludes list, but you're on your own if you do that.
Sorry, but the world keeps moving so the target has to keep moving.

. This project has been reorganized so that the source is in the
subdirectory _src/main/java_, where most *modern* tools
including Maven, Gradle, and others, expect them to be.

. The Java files are in Java packages whose name corresponds 
to the directory they are in (this is how most tools including
Ant and Eclipse expect to find things). 

. The index-bychapter file is substantially out of date since the
packagification "flag day". index-byname.html is reasonably up-to-date.

. I am using Eclipse for most of my development, and Maven for building, and Jenkins
for automated building. Any Ant scripts you find are out of date and unsupported!
If you happen to get them working and want them to stick around, please send me a pull
request against the git repo.

. An unspecified number of files here are used in the Java Cookbook.
Those that have been converted to the newest publishing software at
O'Reilly will have "// BEGIN" and "// END" comments to mark sections for
mechanical inclusion into the book. These are just comments and can be
completely ignored by people looking at the source code normally.
Not all files with these comments necessarily appear in the book.

Ian Darwin

Java Cookbook author

http://www.darwinsys.com/contact.jsp

