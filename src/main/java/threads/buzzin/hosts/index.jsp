<html><head><title>Welcome <%= request.getUserPrincipal() %></title></head>
<!-- HostFun.html -->
<body>
<h1>Message:</h1>
<p>
<%
	String message = (String)session.getAttribute("buzzin.message");
	if (message != null) {
		out.println(message);
	} else {
		out.println();
	}
%>
</p>

<h1>Display Winner</h1>
<p>
<b>The winner is:
<form method="post" action="BuzzInServlet">
	<input type="hidden" name="command" value="show">
	<input type="submit" name="Show" value="Show">
</form>
<h1>Reset Buzzer</h1>
<p>
<b>Remember to RESET before you ask the contestants each question!</b>
<form method="post" action="BuzzInServlet">
	<input type="hidden" name="command" value="reset">
	<input type="submit" name="Reset" value="RESET!">
</form>
