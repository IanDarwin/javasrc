#!/bin/sh

darwinsys_jar="${HOME}/classes/ext/com-darwinsys-all.jar"
tools_jar="/usr/local/jdk1.3.1-linux/lib/tools.jar"

java -classpath ".:${tools_jar}:${darwinsys_jar}" com.darwinsys.tools.jr $*
