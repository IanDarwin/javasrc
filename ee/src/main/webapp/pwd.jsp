<html>
<head>
<title>pwd -- print working directory (aka where r u?)</title>
</head>
</body>
<h1>pwd -- print working directory (aka where r u?)</h1>
<p>This JSP will show where this particular Servlet/JSP engine
will set your ``current directory'' to when runing servlets/JSPs.
In many cases this will be the directory that root/administrator
is in when starting the Web Server.
<%
	java.io.File f = new java.io.File(".");
 %>
<p>Current directory is <%= f.getCanonicalPath() %>
<p>Listing:
<ul>
<% 
	String[] list = f.list();
	for (int i=0; i<list.length; i++) {
		out.print("<li>" + list[i]);
	}
 %>
</ul>
</body>
</html>
