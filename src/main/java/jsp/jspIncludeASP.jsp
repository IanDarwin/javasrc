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

	<% target = "http://www.darwinsys.com/"; %>

	<jsp:include page=target />

</HTML>
