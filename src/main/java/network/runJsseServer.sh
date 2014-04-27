#!/bin/sh

# This is where you created your keystore file as per Chapter 21.
# TLDR short version:
#	keytool -genkey -keystore myKeystore -alias myself
#	keytool -selfcert -alias myself -keystore myKeystore
#	keytool -list -keystore myKeystore
KEYSTOREFILE=myKeystore.jks

if [ ! -f ${KEYSTOREFILE} ]; then
	echo "Can't read keystore file ${KEYSTOREFILE}"
	exit 1
fi

# For demo purposes you can put the self-signed certificate password here:
KEYSTOREPASS=secrit

# Now run it. The classpath setting assumes you built 
# with Maven or Eclipse, or compiled in '.' with javac.
java \
	-Djavax.net.ssl.keyStore=${KEYSTOREFILE} \
	-Djavax.net.ssl.keyStorePassword=${KEYSTOREPASS} \
	-classpath ../../../../target/classes:../../../../build:. \
		network.JSSEWebServer0
