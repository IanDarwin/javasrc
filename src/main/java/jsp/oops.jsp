<%@page isErrorPage="true" import="java.io.*" %>
<html>
<head>
	<title>JSP Demo - OOPS</title>
	<!-- $Id$ -->
</head>
<body bgcolor="black" text="White" link="White" vlink="White">
<h1>JSP Demo -- oops</h1>
<p>We're very sorry, but you've run into a problem with
the JSP Demo application.
Please forward this page to those responsible for this program.
<p><%= exception %>
<pre>
<% exception.printStackTrace(new PrintWriter(out)); %>
<H6>This is $Id$.</H6>
