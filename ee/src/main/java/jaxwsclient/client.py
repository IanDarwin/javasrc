#!/usr/bin/env python

from SOAPpy import WSDL

server = WSDL.Proxy('http://localhost:9090/calc?wsdl')

print "Calc exposes the following methods:"
print wsdl.methods.keys()

server.config.dumpSOAPOut = 1
server.config.dumpSOAPIn = 1

print server.add(34,66)
