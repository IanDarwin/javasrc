#!/usr/bin/perl -w

use SOAP::Lite;

my $calculator = SOAP::Lite
    -> service('http://localhost:9090/calc?wsdl');
print $calculator
    -> add(34,66), "\n";

