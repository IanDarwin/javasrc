fixexcludes:		excludes.xml fixexcludes.awk
	awk -f fixexcludes.awk < pom.xml > pom.new && mv pom.new pom.xml

excludes:		excludes.xml
excludes.xml:	foo
	(cd src/main/java; rg -l COMPILE.ERROR | sed -e 's=.*=						<exclude>&</exclude>='|sort -f) > excludes.xml

foo:
	rm -f excludes.xml
