Warning: This repo is undergoing radical change to reorganize it for Maven/Gradle.
Apologies for any disruption this may cause over the next few days.
Odds are it will soon be move to github for higher-bandwidth hosting...

JavaCook Source Files

Please note the following changes since the Second Edition was published:

*) This is now being built for Java 7. My condolences if you are on some relic
    platform that doesn't have Java 7 or some environment in which Java 7 is
    not yet "approved", but the world has moved on; Java 6 is a relic, is
    deprecated, and has even been EOL'd by Oracle. I am updating the Cookbook
    for Java 8 as you read this; Java 7 is now the minimum. You can probably
    get by with a huge excludes list, but you're on your own if you do that.

*)	The files are being maintained in a public CVS repository; see
	http://javacook.darwinsys.com/download.jsp.
	Also available at projects.darwinsys.com but then you never get updates.

*)	All Java files (all but 2 programs and 2 tests) are now in Java packages whose 
    name corresponds to the directory they are in (this is how most tools including
	Ant and Eclipse expect to find things).
	
*)	The index-bychapter file is substantially out of date since the
	packagification "flag day".

*)	I am using Eclipse for most of my development. This has the side-effect
	that the Ant scripts are now somewhat out of date; if you get them working
	please send me a patch against today's CVS.
	
Sorry for the inconvenience; hopefully these issues will get resolved soon.

Ian Darwin
Java Cookbook author
http://www.darwinsys.com/contact.jsp

