excludes.xml:
	(cd src/main/java; rg -l COMPILE.ERROR | sed -e 's=.*=	<exclude>&</exclude>='|sort) > excludes.xml
