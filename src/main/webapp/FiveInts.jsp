<html>
<head>
<title>Your Personal Random Numbers</title>
<h1>Your Personal Random Numbers</h1>
<p>Here are your personal random numbers,
carefully selected by a
<a href="http://java.com">Java</a> program.
<OL>
	<%
	java.util.Random r = new java.util.Random();
	for (int i=0; i<5; i++) {
		out.print("<li>");
		out.println(r.nextInt());
	}
	%>
</OL>
<HR></HR>
<a href=".">Back to main Page</a>
