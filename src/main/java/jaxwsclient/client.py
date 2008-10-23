#!/usr/bin/env python

from SOAPpy import WSDL

server = WSDL.Proxy('http://localhost:8080/calc?wsdl')

#server.config.dumpSOAPOut = 1
#server.config.dumpSOAPIn = 1

print server.add(34,66)
