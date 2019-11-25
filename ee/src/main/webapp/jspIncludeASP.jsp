<%@ page import="java.io.*, java.net.URL" %>
<!--
 ** This JSP includes output from another site, just to show that it
 ** is possible. DO NOT DO THIS on your public web site without due
 ** diligence being given to the legal and ethical issues!!
 ** And, in this case, check with Microsoft's lawyers first!
 -->
<%
	String PART1="http://search.microsoft.com/us/default.asp?qu=";
	String PART2="&btnSearch=GO&so=RECCNT&boolean=PHRASE&intCat=0&intCat=1&intCat=2&intCat=3&intCat=4&intCat=5&intCat=6&intCat=7&intCat=8&intCat=9&nq=NEW&p=1";
%>
<HTML>
<HEAD><TITLE>Evaluation</TITLE></HEAD>
<BODY BGCOLOR="yellow">

	<%
	String search = request.getParameter("search");
	if (search == null || search.length() == 0) {
	%>
		<P>You did not provide a search string!
		<P>Please use the \"Back\" button and try again.
	<%
		return;
	}
	%>
	<H1>Thank you</H1>

	<P>Here are your search results:</P>
	
	<% String target = PART1 + search + PART2; %>

	Search query is: <%= target %>

	<%
	URL u = new URL(target);
	BufferedReader r = new BufferedReader(
		new InputStreamReader(u.openStream()));
	String line;
	while ((line = r.readLine()) != null)
		// This is a bit lame, as relative img-src and hrefs will come
		// out being relative to the JSP server, not the target server.
		// Should parse the HTML looking for <a and <img tags, and
		// "absolutify" them.
		out.println(line);
	%>

</HTML>
