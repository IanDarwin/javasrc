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

java \
	-Djavax.net.ssl.keyStore=${KEYSTOREFILE} \
	-Djavax.net.ssl.keyStorePassword=${KEYSTOREPASS} \
	-classpath $js/target/classes:. \
		network.JSSEWebServer0
