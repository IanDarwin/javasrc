#!/bin/sh
# Startup script for Ian's WebRedirector
#
# description: Run Ian's WebRedirector

export JAVA_HOME=/usr/java/jdk/

GOTO=http://db2.phenogenomics.ca:8080/

start() {
      echo -n  "Starting Ian's Redirector: "
      java WebRedirector ${GOTO}
      return 0
}

stop() {
      echo "Not shutting down Ian's Redirector "
      return 0
}

case "$1" in
          start) start ;;

          stop) stop ;;

          *) echo "Usage: $0 {start|stop}"
              exit 1

esac

exit 0     
