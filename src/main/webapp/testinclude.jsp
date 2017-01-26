<P>This page shows how both include mechanisms work.
Run it, but then go look at the JSP code generated.
<P>Before jsp-include:</P>
<jsp:include page="demo.txt" flush="true" />
<P>Before at-include</P>
<%@include file="./demo.txt"%>
<P>At end of page</P>
