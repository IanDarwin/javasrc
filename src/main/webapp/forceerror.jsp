<%@page errorPage="oops.jsp"%>
<html>
<p>This page contains code that deliberately throws an exception
to test out the error handling page.
<% String x = null; %>
<%= x.substring(1) %>
