<%@page import="jabadot.*, java.util.*" %>
<%@taglib uri="/jabatags" prefix="jabadot" %>
<!-- 
  -- $Id$
  -->
<HTML>
<jsp:include page="header.html" flush="true"/>
<!-- This page is exempted from the AdServlet policy. Imperiat Rex! -->
<%
	User user = (User)session.getAttribute("jabadot.login");
	// YOU MUST BE LOGGED IN... And BTW ARE YOU AN ADMIN?
	if (user == null || !user.isAdminPrivileged()) {
 %>
<TITLE>Administrators Only</TITLE>
<BODY BGCOLOR="white">
<H1>Administrators Only</H1>
<P>To access this page, you must be logged in as an administrator.
<%	// log("INVALID ADMIN ATTEMPT");
	return;
	}
	// ELSE GO AHEAD...
 %>
<TITLE>JabaDot: SQL Administrator</TITLE>
<BODY BGCOLOR="white">
<H1>JabaDot: SQL Administrator</H1>
<p>
<form action="RawSQLServlet" method="post">
	<textarea name="sql" ROWS="10" COLS="50" WRAP="physical">
	</textarea>
	<br>
	<input type=submit value="Query">
	<input type=submit value="Update">
</form>
