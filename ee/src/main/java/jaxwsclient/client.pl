#!/usr/bin/perl -w

use SOAP::Lite;

my $calculator = SOAP::Lite -> service('http://localhost:9090/calc?wsdl');
print "Calculator add(34,66)...";
print $calculator -> add(34,66), "\n";
print $calculator -> subtract(34,66), "\n";

