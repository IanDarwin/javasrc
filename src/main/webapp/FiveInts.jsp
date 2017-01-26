<HTML>
<HEAD>
<TITLE>Your Personal Random Numbers</TITLE>
<H1>Your Personal Random Numbers</H1>
<P>Here are your personal random numbers,
carefully selected by a
<A HREF=\"http://java.sun.com\">Java</A> program.
<OL>
	<%
	java.util.Random r = new java.util.Random();
	for (int i=0; i<5; i++) {
		out.print("<LI>");
		out.println(r.nextInt());
	}
	%>
</OL>
<HR></HR>
<A HREF=\"index.html\">Back to main Page</A>
