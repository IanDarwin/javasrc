set -x
JARDIR=$HOME/lib
java -classpath .:$JARDIR/darwinsys.jar -Xmx256M Httpd $*
