<html>
<title>Counter JavaBean Demo</title>
<!-- $Id$ -->
<body>
<jsp:useBean class="darwin.Counter" scope="session" id="myCount"/>
<h1>Counter JavaBean Demo</h1>
The counter is now <jsp:getProperty name="myCount" property="count"/>.
<% myCount.incr(); %>
</body>
</html>
