#! /usr/bin/perl
# Perl main program acting as a stub for callbacks from Java

use strict;
use warnings;

# all modules called from either Perl or from Java must go here:
use Text::Levenshtein qw();

use Inline "Java"  => "STUDY",            # glean interface from Java class file
           "AUTOSTUDY" => 1,              # glean more interfaces, too, just in case
           "STUDY" => ["StringDistance"], # name of our main Java class
           "CLASSPATH" => ".",            # needed in order to find main Java class
           ;

my $sd = StringDistance->new(\@ARGV);     # construct instance of main Java class
$sd->show();                              # call routine to show it
$sd->StartCallbackLoop();                 # prepare to listen for threaded callbacks

