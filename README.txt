JavaCook Source Files

Please note the following changes since the Second Edition of the
Java Cookbook was published:

*) This is now being built for Java 7 & 8. Sorry if you are on some relic
    platform that doesn't have Java 7 or some environment in which Java 7 is
    not yet "approved", but the world has moved on; Java 6 is a relic, is
    deprecated, and has been EOL'd by Oracle. I am updating the Cookbook
    for Java 8 as you read this; Java 7 is now the minimum. You can probably
    get by with a huge excludes list, but you're on your own if you do that.

*)	The files are being maintained in a public git repository; see
	https://github.com/IanDarwin/javasrc
	From there you can clone it (to get updates) or download a Zip (but you
	don't get updates).

*)	All Java files (all but 2 programs and 2 tests) are now in Java packages whose 
    name corresponds to the directory they are in (this is how most tools including
	Ant and Eclipse expect to find things).
	
*)	The index-bychapter file is substantially out of date since the
	packagification "flag day".

*)	I am using Eclipse for most of my development. And Maven, not Ant, for
	building. Thus the Ant scripts are now out of date and unsupported; if you get 
	them working please send me a pull request against the git repo.
	

Ian Darwin
Java Cookbook author
http://www.darwinsys.com/contact.jsp

