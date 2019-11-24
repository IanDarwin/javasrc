#! /usr/bin/perl
# Calling Java from Perl, and back again

use strict;
use warnings;

use Text::Levenshtein qw();
  # Perl module from CPAN to measure string similarity

use Inline 0.44 "JAVA" => "DATA";  # pointer to the Inline java source
use Inline::Java qw(caught);  # helper function to determine exception type

my $show = new Showit;     # construct Java object using Perl syntax
$show->show("Just another Perl hacker");            # call method on that object

eval {
  # Call a method that will call back to Perl;
  # catch exceptions, if any.
  print "matcher: ", $show->match("Japh", shift || "Java"), 
	" (displayed from Perl)\n";
};
if ($@) {
  print STDERR "Caught:", caught($@), "\n";
  if (caught("java.lang.Exception")) {
    my $msg = $@->getMessage();
    print STDERR "$msg\n";
  } else {
    die $@;
  }
}


__END__

__JAVA__
// Java starts here
import javax.swing.*;
import org.perl.inline.java.*;

class Showit extends InlineJavaPerlCaller {
  // extension only neeeded if calling back into Perl

  /** Simple Java class to be called from Perl, and to call back to Perl
   */
  public Showit() throws InlineJavaException { }

  /** Simple method */
  public void show(String str) {
    System.out.println(str + " inside Java");
  }

  /** Method calling back into Perl */
  public int match(String target, String pattern)
      throws InlineJavaException, InlineJavaPerlException {

    // Calling a function residing in a Perl Module
    String str = (String)CallPerl("Text::Levenshtein", "distance",
          new Object [] {target, pattern});

    // Show result
    JOptionPane.showMessageDialog(null, "Edit distance between '" + target +
        "' and '" + pattern + "' is " + str,
        "Swinging Perl", JOptionPane.INFORMATION_MESSAGE);
    return Integer.parseInt(str);
  }

}

