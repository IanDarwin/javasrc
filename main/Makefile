excludes:		excludes.xml
excludes.xml:	foo
	(cd src/main/java; rg -l COMPILE.ERROR | sed -e 's=.*=						<exclude>&</exclude>='|sort -f) > excludes.xml

foo:
	rm excludes.xml
