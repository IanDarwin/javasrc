#!/bin/sh
# Startup script for Ian's Trivial server
#
# description: Run Ian's server

start() {
      echo -n  "Starting Ian's Server: "
      java WebServer0
      return 0
}

stop() {
      echo "Not shutting down Ian's Server "
      return 0
}

case "$1" in
          start) start ;;

          stop) stop ;;

          *) echo "Usage: $0 {start|stop}"
              exit 1

esac

exit 0     
